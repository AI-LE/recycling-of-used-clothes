package com.mbyte.easy.wxpay.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class WXConst {
    //微信小程序appid
//    @Value("${weixin.appid}")
    public static String appId = "wx62b5465e91fed50a";
    //微信小程序appsecret
//    @Value("${weixin.secret}")
    public static String appSecret = "8a0165cedc8387dd72ed78a9f9d68bfe";
    //微信支付主体
    public static String title = "测试商品";
    public static String orderNo = "";
    //微信商户号
//    @Value("${weixin.mchId}")
    public static String mch_id="1454147302";
    //微信支付的商户密钥
//    @Value("${weixin.key}")
    public static final String key = "13240419790817131013240419790817";
//    @Value("${weixin.notifyUrl}")
    //支付成功后的服务器回调url
    public static final String notify_url="http://localhost:8080/wxpay/wxNotify";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //微信查询订单接口地址
    public static final String find_order = "https://api.mch.weixin.qq.com/pay/orderquery";

    public static final String transfer_url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    public static final String  apiclient_url = "third/apiclient_cert.p12";
    public static final String  getTransfer_url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";
    //校验用户姓名选项
    public static final String check_name = "NO_CHECK";
//    public static final String check_name = "FORCE_CHECK";
    //付款描述
    public static final String desc = "用户提现";

}
