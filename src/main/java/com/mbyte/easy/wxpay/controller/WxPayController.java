package com.mbyte.easy.wxpay.controller;


import com.alibaba.fastjson.JSONObject;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.entity.OrderGoods;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.service.IOrderGoodsService;
import com.mbyte.easy.recycle.service.IShopOrderService;
import com.mbyte.easy.recycle.service.IWeixinUserService;
import com.mbyte.easy.wxpay.constant.WXConst;
import com.mbyte.easy.wxpay.util.ClientCustomSSLUtil;
import com.mbyte.easy.wxpay.util.PayUtil;
import com.mbyte.easy.wxpay.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/wxpay")
public class WxPayController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PayUtil.class);
    @Autowired
    private IWeixinUserService weixinUserService;

    @Autowired
    private IShopOrderService shopOrderService;
    @Autowired
    private IOrderGoodsService orderGoodsService;

    /**
     * 微信统一下单
     * @param request
     * @param totalFee
     * @return
     */
    @RequestMapping("/topay")
    @ResponseBody
    public AjaxResult topay(HttpServletRequest request, String userId, String totalFee){
        WeixinUser weixinUser = weixinUserService.getById(userId);
        String openId = weixinUser.getOpenId();




        JSONObject json = PayUtil.wxPay(request,openId,totalFee);
        return this.success(json);
    }

    /**
     * 检验是否支付成功接口
     */
    @RequestMapping("/getPayStatus")
    public AjaxResult getPayStatus(String outTradeNo,String orderId){
        try {
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
                shopOrderService.updateById(shopOrder);
            }
            response.put("returnCode",return_code);
            return  this.success(response);
        }catch (Exception e){
                System.out.println(e.getStackTrace());
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
    public AjaxResult transfers(HttpServletRequest request, String userId, String amount){
        try {
            WeixinUser weixinUser = weixinUserService.getById(userId);
            String openId = weixinUser.getOpenId();
            //生成的随机字符串
            String nonce_str = Util.getRandomStringByLength(32);
            //获取本机的ip地址
            String spbill_create_ip = Util.getIpAddr(request);
            //商户订单号(时间戳+随机数)
            int r = (int) ((Math.random() * 9 + 1) * 100000);
            String orderNo  = System.currentTimeMillis()+String.valueOf(r);
            //TODO 修改默认参数
            BigDecimal fee = new BigDecimal(0.01);
            String money = fee.multiply(new BigDecimal("100")).toString().substring(0,fee.multiply(new BigDecimal("100")).toString().indexOf(".")) ;//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
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
            Map<String, Object> response = new HashMap<String, Object>();
            if ( "SUCCESS".equals(return_code)) {
                //TODO 更新用户余额
                System.out.println("*******更新余额");
            }
            response.put("returnCode",return_code);
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
                //TODO 更新用户余额
                System.out.println("*******更新余额");
            }
            response.put("returnCode",return_code);
            return  this.success(response);

        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
        return  this.success();
    }

}
