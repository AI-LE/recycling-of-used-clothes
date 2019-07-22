package com.mbyte.easy.wxpay.controller;


import com.alibaba.fastjson.JSONObject;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.wxpay.util.PayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
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
     * 回调函数
     * @return
     */
    @RequestMapping("/wxNotify")
    public static void wxNotify(HttpServletRequest request, HttpServletResponse response){
           try {
               PayUtil.wxNotify(request, response);
           }catch (Exception e){
               logger.info(e.getMessage());
           }
    }


}
