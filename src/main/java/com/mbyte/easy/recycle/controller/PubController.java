package com.mbyte.easy.recycle.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.entity.UserProp;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.mapper.UserPropMapper;
import com.mbyte.easy.recycle.service.IUserPropService;
import com.mbyte.easy.recycle.service.IWeixinUserService;
import com.mbyte.easy.vo.WeChatAppLoginReq;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/pub")
public class PubController extends BaseController {

    @Autowired
    private IWeixinUserService weixinUserService;
    @Resource
    private IUserPropService userPropService;
    @Resource
    private UserPropMapper userPropMapper;
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
    public AjaxResult getOpenId(@ModelAttribute WeChatAppLoginReq req) throws IOException {

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
            Map<String, Object> map = new HashMap<String, Object>();
            String openId = jsonObject.getString("openid");
            map.put("openId", openId);
            return success(map);
        }

        return error();
    }

    /**
     * 绑定用户信息
     */
    @RequestMapping(value = {"/setInfo"})
    public AjaxResult setInfo(WeixinUser weixinUser){
        QueryWrapper<WeixinUser> queryWrapper = new QueryWrapper<WeixinUser>();
        if (weixinUser.getOpenId() != null && !"".equals(weixinUser.getOpenId())) {
                queryWrapper = queryWrapper.eq("openId", weixinUser.getOpenId());
            }
            WeixinUser oldUser = weixinUserService.getOne(queryWrapper);
            if (oldUser != null) {
                weixinUserService.update(weixinUser, queryWrapper);
            } else {
                weixinUserService.save(weixinUser);
            }
        return this.success();
    }


    /**
     * 个人收取货地址列表
     * @return
     */
    @RequestMapping(value = {"/getAddressList"})
    public  AjaxResult getAddressList(Long userId){
        if(userId != null || !"".equals(userId)) {
            List<UserProp> addressList = userPropMapper.getAddressList(userId);
            return this.success(addressList);
        }
        return  this.error();
    }

    /**
     * 删除收取货地址
     * @return
     */
    @RequestMapping(value = {"/removeAddress"})
    public AjaxResult removeAddress(Long addressId){
        if(addressId != null ||  !"".equals(addressId)) {
            UserProp userProp = new UserProp();
            userProp.setIsDel(1);
            userProp.setId(addressId);
            userPropService.updateById(userProp);
            return  this.success();
        }
        return  this.error();
    }

    /**
     * 增加收取货地址
     * @return
     */
    @RequestMapping(value = {"/setAddress"})
    public  AjaxResult setAddress(UserProp userProp){
        if (userProp.getUserId()!= null || !"".equals(userProp.getUserId())){
            userPropService.save(userProp);
            System.out.println();
            return  this.success();
        }
        return  this.error();
    }
    /**
     * 修改收取货地址
     * @return
     */
    @RequestMapping(value = {"/updateAddress"})
    public  AjaxResult updateAddress(UserProp userProp){
        if ( !"".equals(userProp.getId())){
            userPropService.updateById(userProp);
            return  this.success();
        }
        return  this.error();
    }

    @RequestMapping("back")
    @ResponseBody
    public String backTest(){
        return "backTest";
    }

}
