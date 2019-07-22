package com.mbyte.easy.wxpay.controller;


import com.alibaba.fastjson.JSONObject;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.wxpay.util.PayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    public AjaxResult getPayStatus(){

        return this.success();
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
