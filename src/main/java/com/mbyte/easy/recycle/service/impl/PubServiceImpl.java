package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.ProductModel;
import com.mbyte.easy.recycle.service.IPubService;
import com.mbyte.easy.util.wxpay.sdk.WXPay;
import com.mbyte.easy.util.wxpay.sdk.WXPayProerties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 微信字符服务类
 * @author KevinLyz 李曜铮
 * @since 2019-07-18
 * @Version 1.0
 */
@Service
public class PubServiceImpl implements IPubService {
    private final static Logger logger = LoggerFactory.getLogger(PubServiceImpl.class);

    @Autowired
    private WXPayProerties wxPayProerties;

    /**
     * 微信支付接口
     * @return String
     * @param product
     */
    @Override
    public int wxPay(ProductModel product) {

        logger.info("订单号：{}生成微信支付码",product);
        try {
            WXPay wxpay = new WXPay(wxPayProerties);
            Map<String, String> data = new HashMap<String, String>(10);
//            data.put("body",product.getSubject());
//            data.put("out_trade_no",product.getOutTradeNo());
            data.put("orderId",product.getOrderId());
            data.put("device_info", "WEB");
            data.put("fee_type", "CNY");
//            data.put("total_fee", PayUtil.handleTotalFee(product.getTotalFee()));
            data.put("notify_url", wxPayProerties.getNotifyUrl());
            // 此处指定为扫码支付
            data.put("trade_type", "APP");
            // 统一下单
            Map<String, String> map = wxpay.unifiedOrder(data);
            if (map != null){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }
}
