package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.service.IRecycleOrderService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author 艾乐
* @since 2019-03-11
*/
@Controller
@RequestMapping("/recycle/recycleOrder")
public class RecycleOrderController extends BaseController  {

    private String prefix = "recycle/recycleOrder/";

    @Autowired
    private IRecycleOrderService recycleOrderService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param recycleOrder
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String appointmentSpace, String createtimeSpace, String updatetimeSpace, RecycleOrder recycleOrder) {
        Page<RecycleOrder> page = new Page<RecycleOrder>(pageNo, pageSize);
        QueryWrapper<RecycleOrder> queryWrapper = new QueryWrapper<RecycleOrder>();
        if(!ObjectUtils.isEmpty(recycleOrder.getOrderNo())) {
            queryWrapper = queryWrapper.like("order_no",recycleOrder.getOrderNo());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getUserId())) {
            queryWrapper = queryWrapper.like("user_id",recycleOrder.getUserId());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getCourierId())) {
            queryWrapper = queryWrapper.like("courier_id",recycleOrder.getCourierId());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getPrice())) {
            queryWrapper = queryWrapper.like("price",recycleOrder.getPrice());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getAppointment())) {
            queryWrapper = queryWrapper.like("appointment",recycleOrder.getAppointment());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getAddressId())) {
            queryWrapper = queryWrapper.like("address_id",recycleOrder.getAddressId());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getPhone())) {
            queryWrapper = queryWrapper.like("phone",recycleOrder.getPhone());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getPickCode())) {
            queryWrapper = queryWrapper.like("pick_code",recycleOrder.getPickCode());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getStatus())) {
            queryWrapper = queryWrapper.like("status",recycleOrder.getStatus());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getCreatetime())) {
            queryWrapper = queryWrapper.like("createtime",recycleOrder.getCreatetime());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getUpdatetime())) {
            queryWrapper = queryWrapper.like("updatetime",recycleOrder.getUpdatetime());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getIsShow())) {
            queryWrapper = queryWrapper.like("is_show",recycleOrder.getIsShow());
         }
        if(!ObjectUtils.isEmpty(recycleOrder.getIsDel())) {
            queryWrapper = queryWrapper.like("is_del",recycleOrder.getIsDel());
         }
        IPage<RecycleOrder> pageInfo = recycleOrderService.page(page, queryWrapper);
        model.addAttribute("appointmentSpace", appointmentSpace);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("updatetimeSpace", updatetimeSpace);
        model.addAttribute("searchInfo", recycleOrder);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }
    /**
    * 添加
    * @param recycleOrder
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(RecycleOrder recycleOrder){
        return toAjax(recycleOrderService.save(recycleOrder));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("recycleOrder",recycleOrderService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param recycleOrder
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(RecycleOrder recycleOrder){
        return toAjax(recycleOrderService.updateById(recycleOrder));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(recycleOrderService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(recycleOrderService.removeByIds(ids));
    }

}

