package com.mbyte.easy.rest.weixin_user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.service.IWeixinUserService;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author Author
 * @since 2019-03-11
 */
@RestController
@RequestMapping("/rest/weixinUser")
public class RestWeixinUserController extends BaseController {
    private String prefix = "recycle/weixinUser/";

    @Autowired
    private IWeixinUserService weixinUserService;


    /**
     * 查询列表
     *
     * @param model
     * @param weixinUser
     * @return
     */
    @RequestMapping
    public AjaxResult index(Model model, WeixinUser weixinUser) {
        weixinUser=weixinUserService.getById(weixinUser.getId());
        return this.success(weixinUser);
    }

}
