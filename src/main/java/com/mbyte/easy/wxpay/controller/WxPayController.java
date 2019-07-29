package com.mbyte.easy.wxpay.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.entity.Rate;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.entity.TransferDetail;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.service.*;
import com.mbyte.easy.wxpay.constant.WXConst;
import com.mbyte.easy.wxpay.util.ClientCustomSSLUtil;
import com.mbyte.easy.wxpay.util.PayUtil;
import com.mbyte.easy.wxpay.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 微信支付、提现及订单相关业务逻辑
 * @author 秦策
 */

@RestController
@RequestMapping("/wxpay")
public class WxPayController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PayUtil.class);
    @Autowired
    private IWeixinUserService weixinUserService;

    @Autowired
    private IShopOrderService shopOrderService;

    @Autowired
    private ITransferDetailService transferDetailService;
    @Autowired
    private IRateService rateService;

    /**
     * 获得提现比率
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getTransferRate")
    @ResponseBody
    public AjaxResult getTransferRate(){
        List<Rate> rate = rateService.list();
        BigDecimal transferRate = new BigDecimal(rate.get(0).getWithdrawalRate().toString());
        return this.success(transferRate);
    }

    /**
     * 获得实际提现金额
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getTransferMoney")
    @ResponseBody
    public AjaxResult getTransferMoney(BigDecimal amount){
        List<Rate> rate = rateService.list();
        BigDecimal transferRate = new BigDecimal(rate.get(0).getWithdrawalRate().toString());
        BigDecimal money = amount.multiply(transferRate).setScale(2,BigDecimal.ROUND_HALF_UP);
        return this.success(money);
    }


    /**
     * 获得支付比率
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getPayRate")
    @ResponseBody
    public AjaxResult getPayRate(){
        List<Rate> rate = rateService.list();
        BigDecimal payRate = new BigDecimal(rate.get(0).getPayRate().toString());
        return this.success(payRate);
    }

    /**
     * 获得抵扣积分
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getPoints")
    @ResponseBody
    public AjaxResult getPoints(BigDecimal fee){
        List<Rate> rate = rateService.list();
        BigDecimal payRate = new BigDecimal(rate.get(0).getPayRate().toString());
        BigDecimal points = fee.divide(payRate).setScale(2,BigDecimal.ROUND_HALF_UP);
        return this.success(points);
    }

    /**
     * 获得实际支付金额
     * @param
     * @param
     * @return
     */
    @RequestMapping("/getPayMoney")
    @ResponseBody
    public AjaxResult getPayMoney(Long userId){
       WeixinUser weixinUser = weixinUserService.getById(userId);
        List<WeixinUser>weixinUserList=weixinUserService.list();
        BigDecimal price = weixinUser.getAccount();
        List<Rate> rate = rateService.list();
        BigDecimal payRate = new BigDecimal(rate.get(0).getPayRate().toString());
        BigDecimal money = price.multiply(payRate).setScale(2,BigDecimal.ROUND_HALF_UP);
        return this.success(money);
    }


    /**
     * 微信统一下单
     * @param request
     * @param totalFee
     * @return
     */
    @RequestMapping("/topay")
    @ResponseBody
    public AjaxResult topay(HttpServletRequest request, Long userId, String totalFee){
        WeixinUser weixinUser = weixinUserService.getById(userId);
        String openId = weixinUser.getOpenId();
        JSONObject json = PayUtil.wxPay(request,openId,totalFee);
        return this.success(json);
    }

    /**
     * 检验是否支付成功接口
     */
    @RequestMapping("/getPayStatus")
    public AjaxResult getPayStatus(String outTradeNo,String orderId,Long userId,BigDecimal payMoney,String payStyle){
        try {
            BigDecimal totalPoints = new BigDecimal(0);
            //判断是否为混合支付
            if(StringUtils.isNotEmpty(payStyle)){
                WeixinUser weixinUser = weixinUserService.getById(userId);
                totalPoints = weixinUser.getAccount();
            }
            //生成的随机字符串
            String nonce_str = Util.getRandomStringByLength(32);
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WXConst.appId);
            packageParams.put("mch_id", WXConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("out_trade_no", outTradeNo);//商户订单号
            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WXConst.key, "utf-8").toUpperCase();
            logger.info("=======================第一次签名：" + mysign + "=====================");
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>" + "<appid>" + WXConst.appId + "</appid>"
                    + "<mch_id>" + WXConst.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<out_trade_no>" + outTradeNo + "</out_trade_no>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WXConst.find_order, "POST", xml);
            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            Map<String, Object> response = new HashMap<String, Object>();
            if ( "SUCCESS".equals(return_code)) {
                //支付完成修改订单状态
                ShopOrder shopOrder = new ShopOrder();
                shopOrder.setId(Long.parseLong(orderId));
                shopOrder.setStatus(2);
                shopOrder.setPoints(totalPoints);
                shopOrder.setWxMoney(payMoney);
                shopOrderService.updateById(shopOrder);
                if(StringUtils.isNotEmpty(payStyle)) {
                WeixinUser weixinUser = weixinUserService.getById(userId);
                    weixinUser.setAccount(new BigDecimal(0.00));
                    weixinUserService.updateById(weixinUser);
                }
            }
            response.put("returnCode",return_code);
            return  this.success(response);
        }catch (Exception e){
                e.getStackTrace();
        }

        return this.error();
    }

    /**
     * 小程序支付API回调函数
     * @return
     */
//    @RequestMapping("/wxNotify")
//    public void wxNotify(HttpServletRequest request, HttpServletResponse response){
//           try {
//               System.out.println("************************执行回调函数");
//               PayUtil.wxNotify(request, response);
//               System.out.println("************************完成回调函数");
//           }catch (Exception e){
//               logger.info(e.getMessage());
//           }
//    }

    /**
     * 提现接口
     * @return
     */
    @RequestMapping("/transfers")
    public AjaxResult transfers(HttpServletRequest request, Long userId, BigDecimal amount){
        try {

//            //获得比率
//            List<Rate> rate = rateService.list();
//            BigDecimal transferRate = new BigDecimal(rate.get(0).getWithdrawalRate().toString());
//            System.out.println("提现比率"+transferRate);
            WeixinUser weixinUser = weixinUserService.getById(userId);
            String openId = weixinUser.getOpenId();
            //生成的随机字符串
            String nonce_str = Util.getRandomStringByLength(32);
            //获取本机的ip地址
            String spbill_create_ip = Util.getIpAddr(request);
            //商户订单号(时间戳+随机数)
            int r = (int) ((Math.random() * 9 + 1) * 100000);
            String orderNo  = System.currentTimeMillis()+String.valueOf(r);

            //按比例换算提现金额,单位：分，这边需要转成字符串类型，否则后面的签名会失败
            String money = amount.multiply(new BigDecimal("100")).toString();

            //生成订单
            TransferDetail transferDetail = new TransferDetail();
            transferDetail.setCreatetime(LocalDateTime.now());
            transferDetail.setPrice(amount);
            transferDetail.setUserId(userId);
            transferDetail.setTransferNo(orderNo);
            transferDetailService.save(transferDetail);

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("mch_appid", WXConst.appId);
            packageParams.put("mchid", WXConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("partner_trade_no", orderNo);//商户订单号
            packageParams.put("openid", openId);
            packageParams.put("check_name", WXConst.check_name);
            packageParams.put("amount", money);
            packageParams.put("desc", WXConst.desc);
            packageParams.put("spbill_create_ip", spbill_create_ip);
            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串


            //MD5运算生成签名，签名
            String mysign = PayUtil.sign(prestr, WXConst.key, "utf-8").toUpperCase();
            logger.info("=======================签名：" + mysign + "=====================");
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>"
                    + "<mch_appid>" + WXConst.appId + "</mch_appid>"
                    +"<mchid>" + WXConst.mch_id + "</mchid>"
                    +"<nonce_str>" + nonce_str + "</nonce_str>"
                    +"<partner_trade_no>" + orderNo + "</partner_trade_no>"
                    +"<openid>" + openId + "</openid>"
                    +"<check_name>" + WXConst.check_name + "</check_name>"
                    +"<amount>" + money + "</amount>"
                    +"<desc>" + WXConst.desc + "</desc>"
                    +"<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    +"<sign>" + mysign + "</sign>"
                    +"</xml>";
            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
            //调用企业支付接口，并接受返回的结果
            String result = ClientCustomSSLUtil.doRefund(WXConst.transfer_url,xml);
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            String result_code = (String) map.get("result_code");
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("returnCode",return_code);
            response.put("resultCode",result_code);
            response.put("partnerTradeNo",orderNo);
            return  this.success(response);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
        return this.success();
    }

    /**
     * 检验是否提现成功接口
     */
    @RequestMapping("/getTransferStatus")
    public AjaxResult getTransferStatus(String partnerTradeNo){
        try {
            //生成的随机字符串
            String nonce_str = Util.getRandomStringByLength(32);
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WXConst.appId);
            packageParams.put("mch_id", WXConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("partner_trade_no", partnerTradeNo);//商户订单号
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串


            //MD5运算生成签名，签名
            String mysign = PayUtil.sign(prestr, WXConst.key, "utf-8").toUpperCase();
            logger.info("=======================签名：" + mysign + "=====================");
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml version='1.0' encoding='gbk'>"
                    + "<appid>" + WXConst.appId + "</appid>"
                    + "<mch_id>" + WXConst.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<partner_trade_no>" + partnerTradeNo + "</partner_trade_no>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
            String result = ClientCustomSSLUtil.doRefund(WXConst.getTransfer_url, xml);
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            Map<String, Object> response = new HashMap<String, Object>();
            if ( "SUCCESS".equals(return_code)) {
                //业务逻辑
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("transfer_no",partnerTradeNo);
                TransferDetail transferDetail = transferDetailService.getOne(queryWrapper);
                weixinUserService.getById(transferDetail.getUserId());
                WeixinUser weixinUser = new WeixinUser();
                weixinUser.setId(transferDetail.getUserId());
                weixinUser.setAccount(weixinUser.getAccount().subtract(transferDetail.getPrice()));
                weixinUserService.updateById(weixinUser);
                transferDetail.setStatus(2);
                transferDetailService.updateById(transferDetail);
            }
            response.put("returnCode",return_code);
            return  this.success(response);

        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return  this.success();
    }

}
