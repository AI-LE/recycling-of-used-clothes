package com.mbyte.easy.rest.userProp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.UserProp;
import com.mbyte.easy.recycle.service.IUserPropService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.rest.recycleOrder.RestRecycleOrderController;
import com.mbyte.easy.util.PageInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

/**
* <p>
* 前端控制器
* </p>
* @author 艾乐
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/userProp")
public class RestUserPropController extends BaseController  {

    private static final Logger logger = LoggerFactory.getLogger(RestUserPropController.class);

    @Autowired
    private IUserPropService userPropService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param userProp
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, UserProp userProp) {
        Page<UserProp> page = new Page<UserProp>(pageNo, pageSize);
        QueryWrapper<UserProp> queryWrapper = new QueryWrapper<UserProp>();

        if(userProp.getUserId() != null  && !"".equals(userProp.getUserId() + "")) {
            queryWrapper = queryWrapper.like("user_id",userProp.getUserId());
         }


        if(userProp.getUserName() != null  && !"".equals(userProp.getUserName() + "")) {
            queryWrapper = queryWrapper.like("user_name",userProp.getUserName());
         }


        if(userProp.getPhone() != null  && !"".equals(userProp.getPhone() + "")) {
            queryWrapper = queryWrapper.like("phone",userProp.getPhone());
         }


        if(userProp.getAddress() != null  && !"".equals(userProp.getAddress() + "")) {
            queryWrapper = queryWrapper.like("address",userProp.getAddress());
         }


        if(userProp.getIsDel() != null  && !"".equals(userProp.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("is_del",userProp.getIsDel());
         }

        IPage<UserProp> pageInfo = userPropService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  userProp);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }

    @RequestMapping("select")
    public AjaxResult select(@RequestParam("userId") Long userId){

        QueryWrapper<UserProp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        UserProp userProp = userPropService.getOne(queryWrapper);
        Map<String,UserProp> map = new HashMap<>();
        map.put("userProp",userProp);
        return this.success(map);

    }

    /**
     * 添加
     * @param address
     * @param userId
     * @param phone
     * @param userName
     * @return
     */
    @RequestMapping("add")
    public AjaxResult add(@RequestParam("address") String address,@RequestParam("userId") Long userId,
                          @RequestParam("phone") String phone,@RequestParam("userName") String userName){
        UserProp userProp = new UserProp();
        userProp.setIsDel(2);
        userProp.setAddress(address);
        userProp.setUserId(userId);
        userProp.setPhone(phone);
        userProp.setUserName(userName);
        Map<String,Long> map = new HashMap<>();
        QueryWrapper<UserProp> queryWrapper = new QueryWrapper<>();
        queryWrapper = queryWrapper.eq("user_id",userId);
        if(userPropService.getOne(queryWrapper) != null){
            UpdateWrapper<UserProp> updateWrapper = new UpdateWrapper<>();
            updateWrapper = updateWrapper.eq("user_id",userId);
            userPropService.update(userProp,updateWrapper);
        }
        else {
            userPropService.insertUserProp(userProp);
        }
        map.put("id",userProp.getId());
        logger.info(userProp.getId() + "");
        return this.success(map);
    }

    /**
    * 添加
    * @param userProp
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(UserProp userProp){
        return toAjax(userPropService.updateById(userProp));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(userPropService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(userPropService.removeByIds(ids));
    }

}

