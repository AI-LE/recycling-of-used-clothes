package com.mbyte.easy.rest.recycleOrder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.service.IRecycleOrderService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
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

    @Autowired
    private IRecycleOrderService recycleOrderService;

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


        if(recycleOrder.getIsShow() != null  && !"".equals(recycleOrder.getIsShow() + "")) {
            queryWrapper = queryWrapper.like("is_show",recycleOrder.getIsShow());
         }


        if(recycleOrder.getIsDel() != null  && !"".equals(recycleOrder.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("is_del",recycleOrder.getIsDel());
         }

        IPage<RecycleOrder> pageInfo = recycleOrderService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  recycleOrder);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加订单
    * @param recycleOrder
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(RecycleOrder recycleOrder){
        LocalDateTime time = LocalDateTime.now();
        recycleOrder.setCreatetime(time);
        recycleOrder.setUpdatetime(time);
        return toAjax(recycleOrderService.save(recycleOrder));
    }

    /**
    * 编辑，对订单的审核，状态的变化
    * @param recycleOrder
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(RecycleOrder recycleOrder){
        recycleOrder.setUpdatetime(LocalDateTime.now());
        return toAjax(recycleOrderService.updateById(recycleOrder));
    }

    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(recycleOrderService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(recycleOrderService.removeByIds(ids));
    }

}

