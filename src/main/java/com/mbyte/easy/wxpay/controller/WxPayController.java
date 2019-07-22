package com.mbyte.easy.wxpay.controller;


import com.alibaba.fastjson.JSONObject;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.wxpay.constant.WXConst;
import com.mbyte.easy.wxpay.util.PayUtil;
import com.mbyte.easy.wxpay.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/wxpay")
public class WxPayController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(PayUtil.class);
    /**
     * 微信统一下单
     * @param request
     * @param openId
     * @param totalFee
     * @return
     */
    @RequestMapping("/topay")
    @ResponseBody
    public AjaxResult topay(HttpServletRequest request, @RequestParam("openid") String openId, @RequestParam("total_fee") String totalFee){
        JSONObject json = PayUtil.wxPay(request,openId,totalFee);
        return this.success(json);
    }

    /**
     * 检验是否支付成功接口
     */
    @RequestMapping("/getPayStatus")
    public AjaxResult getPayStatus(String outTradeNo){
        try {
            //生成的随机字符串
            String nonce_str = Util.getRandomStringByLength(32);
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WXConst.appId);
            packageParams.put("mch_id", WXConst.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("out_trade_no", outTradeNo);//商户订单号
//            packageParams.put("trade_type", WXConst.TRADETYPE);

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

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WXConst.find_order, "POST", xml);
            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            Map<String, Object> response = new HashMap<String, Object>();
            if ( "SUCCESS".equals(return_code)) {
                //TODO 更新订单状态
                System.out.println("*******更新订单状态");
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
    @RequestMapping("/wxNotify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response){
           try {
               System.out.println("************************执行回调函数");
               PayUtil.wxNotify(request, response);
               System.out.println("************************完成回调函数");
           }catch (Exception e){
               logger.info(e.getMessage());
           }
    }


}
