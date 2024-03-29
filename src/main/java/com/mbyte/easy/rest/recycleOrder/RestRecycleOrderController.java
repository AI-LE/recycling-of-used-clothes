package com.mbyte.easy.rest.recycleOrder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.entity.UserProp;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.mapper.UserPropMapper;
import com.mbyte.easy.recycle.service.IRecycleOrderService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IUserPropService;
import com.mbyte.easy.recycle.service.IWeixinUserService;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.PageInfo;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
* <p>
* 前端控制器
* </p>
* @author 艾乐
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/recycleOrder")
public class RestRecycleOrderController extends BaseController  {

    private static final Logger logger = LoggerFactory.getLogger(RestRecycleOrderController.class);

    @Autowired
    private IRecycleOrderService recycleOrderService;

    @Autowired
    private IWeixinUserService iWeixinUserService;

    @Resource
    private UserPropMapper userPropMapper;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param recycleOrder
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String appointmentSpace, String createtimeSpace, String updatetimeSpace, RecycleOrder recycleOrder) {
        Page<RecycleOrder> page = new Page<RecycleOrder>(pageNo, pageSize);
        QueryWrapper<RecycleOrder> queryWrapper = new QueryWrapper<RecycleOrder>();

        if(recycleOrder.getOrderNo() != null  && !"".equals(recycleOrder.getOrderNo() + "")) {
            queryWrapper = queryWrapper.like("order_no",recycleOrder.getOrderNo());
         }


        if(recycleOrder.getUserId() != null  && !"".equals(recycleOrder.getUserId() + "")) {
            queryWrapper = queryWrapper.like("user_id",recycleOrder.getUserId());
         }


        if(recycleOrder.getCourierId() != null  && !"".equals(recycleOrder.getCourierId() + "")) {
            queryWrapper = queryWrapper.like("courier_id",recycleOrder.getCourierId());
         }


        if(recycleOrder.getPrice() != null  && !"".equals(recycleOrder.getPrice() + "")) {
            queryWrapper = queryWrapper.like("price",recycleOrder.getPrice());
         }


        if(recycleOrder.getAppointment() != null  && !"".equals(recycleOrder.getAppointment() + "")) {
            queryWrapper = queryWrapper.like("appointment",recycleOrder.getAppointment());
         }


        if(recycleOrder.getAddressId() != null  && !"".equals(recycleOrder.getAddressId() + "")) {
            queryWrapper = queryWrapper.like("address_id",recycleOrder.getAddressId());
         }


        if(recycleOrder.getPhone() != null  && !"".equals(recycleOrder.getPhone() + "")) {
            queryWrapper = queryWrapper.like("phone",recycleOrder.getPhone());
         }


        if(recycleOrder.getPickCode() != null  && !"".equals(recycleOrder.getPickCode() + "")) {
            queryWrapper = queryWrapper.like("pick_code",recycleOrder.getPickCode());
         }


        if(recycleOrder.getStatus() != null  && !"".equals(recycleOrder.getStatus() + "")) {
            queryWrapper = queryWrapper.like("status",recycleOrder.getStatus());
         }


        if(recycleOrder.getCreatetime() != null  && !"".equals(recycleOrder.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",recycleOrder.getCreatetime());
         }


        if(recycleOrder.getUpdatetime() != null  && !"".equals(recycleOrder.getUpdatetime() + "")) {
            queryWrapper = queryWrapper.like("updatetime",recycleOrder.getUpdatetime());
         }


        queryWrapper = queryWrapper.eq("is_show",1);


        queryWrapper = queryWrapper.eq("is_del",1);

        IPage<RecycleOrder> pageInfo = recycleOrderService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  recycleOrder);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    @RequestMapping("uploadImage")
    public AjaxResult uploadImage(@RequestParam("file") MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        Map<String,String> map = new HashMap<>();
        map.put("imageUrl","../images/" + FileUtil.uploadFile(multipartFile));
        return success(map);
    }

    /**
    * 添加订单
    * @return
    */
    @RequestMapping("add")
    public AjaxResult add(@RequestParam("price") BigDecimal price, @RequestParam("appointment") LocalDateTime appointment,
                          @RequestParam("phone") String phone,  @RequestParam("userId") Long userId,
                          @RequestParam("userName") String userName, @RequestParam("address") String address,
                          @RequestParam("imageUrl") String imageUrl){

        UserProp userProp = new UserProp();
        userProp.setUserName(userName);
        userProp.setPhone(phone);
        userProp.setAddress(address);
        userProp.setUserId(userId);
        userPropMapper.addAddress(userProp);
        LocalDateTime time = LocalDateTime.now();
        RecycleOrder recycleOrder = new RecycleOrder();
        recycleOrder.setAppointment(appointment);
        recycleOrder.setUserId(userId);
        recycleOrder.setAddressId(userProp.getId());
        recycleOrder.setPhone(phone);
        recycleOrder.setPrice(price);
        recycleOrder.setImageUrl(imageUrl);
        recycleOrder.setCreatetime(time);
        recycleOrder.setUpdatetime(time);
        recycleOrder.setOrderNo(time.toString().replaceAll("[-:.T]",""));   //随机生成订单号
        recycleOrder.setIsDel(2);
        recycleOrder.setIsShow(2);
        recycleOrder.setStatus(1);
        recycleOrder.setPickCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0,11));
        return toAjax(recycleOrderService.save(recycleOrder));
    }

    /**
     * 根据条件查询订单
     * @param status
     * @param userId
     * @param courierId
     * @return
     */
    @RequestMapping("select")
    public AjaxResult select(@RequestParam(value = "status", required = false)Integer status, @RequestParam(value = "userId",required = false) Long userId, @RequestParam(value = "courierId",required = false) Long courierId){

        QueryWrapper<RecycleOrder> queryWrapper = new QueryWrapper<RecycleOrder>().orderByDesc("createtime");
        if(userId != null){
            queryWrapper = queryWrapper.eq("user_id",userId);
        }
        if(courierId != null){
            queryWrapper = queryWrapper.eq("courier_id",courierId);
        }
        if(status != null){
            queryWrapper = queryWrapper.eq("status",status);
        }
        List<RecycleOrder> recycleOrder = recycleOrderService.list(queryWrapper);
        Map<String,List<RecycleOrder>> map = new HashMap<>();
        map.put("recycleOrder",recycleOrder);
        return this.success(map);
    }

    /**
     * 编辑，对订单的审核，状态的变化
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("edit")
    public AjaxResult edit(@PathParam("id") Long id, @PathParam("status") Integer status){
        RecycleOrder recycleOrder = new RecycleOrder();
        recycleOrder.setId(id);
        recycleOrder.setStatus(status);
        recycleOrder.setUpdatetime(LocalDateTime.now());
        return toAjax(recycleOrderService.updateById(recycleOrder));
    }

    /**
     * 取货员付款，订单完成
     */
    @RequestMapping("pickUp")
    public AjaxResult pickUp(@PathParam("userId") Long userId, @PathParam("id") Long id, @RequestParam("price") BigDecimal balance){
        RecycleOrder recycleOrder = new RecycleOrder();
        recycleOrder.setId(id);
        recycleOrder.setStatus(4);
        recycleOrder.setUpdatetime(LocalDateTime.now());
        iWeixinUserService.updateBalance(balance,userId);
         return toAjax(recycleOrderService.updateById(recycleOrder));
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @RequestMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(recycleOrderService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @RequestMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(recycleOrderService.removeByIds(ids));
    }

}

