package com.mbyte.easy.recycle.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.vo.WeChatAppLoginReq;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/pub")
public class PubController extends BaseController {

    /**
     * TODO 小程序appid
     **/
    @Value("${weixin.appid}")
    private String appid = "";

    /**
     * TODO 小程序appSecret
     **/
    @Value("${weixin.secret}")
    private String secret = "";

    /**
     * 获得微信授权
     */
    @RequestMapping(value = {"/getOpenId"})
    public AjaxResult getOpenId(@ModelAttribute WeChatAppLoginReq req, String encryptedData, String iv) throws IOException {

        /**
         * TODO 小程序登录时获取的 code
         **/
        String jsCode = req.getCode();
        /**
         * TODO 授权类型，此处只需填写 authorization_code
         **/
        String grantType = "authorization_code";

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=" + grantType;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse res = client.execute(httpGet);
        if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            HttpEntity entity = res.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonObject = JSON.parseObject(result);
            WeixinUser weixinUser = new WeixinUser();
            System.out.println(jsonObject);
            String openid = jsonObject.get("openid").toString();
            weixinUser.setOpenId(openid);
            QueryWrapper<WeixinUser> queryWrapper = new QueryWrapper<WeixinUser>();
            if (weixinUser.getOpenId() != null && !"".equals(weixinUser.getOpenId())) {
                queryWrapper = queryWrapper.eq("openId", weixinUser.getOpenId());
            }
//            WeixinUser oldUser = weixinUserService.getOne(queryWrapper);
//            weixinUser.setCreatetime(LocalDateTime.now());
//
//            if (oldUser != null) {
//                weixinUserService.update(weixinUser, queryWrapper);
//            } else {
//                weixinUserService.save(weixinUser);
//            }
            Map<String, Object> map = new HashMap<String, Object>();
            String openId = jsonObject.getString("openid");
            map.put("openId", openId);
            return success(map);
        }

        return error();
    }


    @RequestMapping("back")
    @ResponseBody
    public String backTest(){
        return "backTest";
    }

}
