package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.OrderCourierRel;
import com.mbyte.easy.recycle.service.IOrderCourierRelService;
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
@RequestMapping("/recycle/orderCourierRel")
public class OrderCourierRelController extends BaseController  {

    private String prefix = "recycle/orderCourierRel/";

    @Autowired
    private IOrderCourierRelService orderCourierRelService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param orderCourierRel
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, OrderCourierRel orderCourierRel) {
        Page<OrderCourierRel> page = new Page<OrderCourierRel>(pageNo, pageSize);
        QueryWrapper<OrderCourierRel> queryWrapper = new QueryWrapper<OrderCourierRel>();
        if(!ObjectUtils.isEmpty(orderCourierRel.getOrderId())) {
            queryWrapper = queryWrapper.like("order_id",orderCourierRel.getOrderId());
         }
        if(!ObjectUtils.isEmpty(orderCourierRel.getCourierId())) {
            queryWrapper = queryWrapper.like("courier_id",orderCourierRel.getCourierId());
         }
        if(!ObjectUtils.isEmpty(orderCourierRel.getPickCode())) {
            queryWrapper = queryWrapper.like("pick_code",orderCourierRel.getPickCode());
         }
        IPage<OrderCourierRel> pageInfo = orderCourierRelService.page(page, queryWrapper);
        model.addAttribute("searchInfo", orderCourierRel);
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
    * @param orderCourierRel
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(OrderCourierRel orderCourierRel){
        return toAjax(orderCourierRelService.save(orderCourierRel));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("orderCourierRel",orderCourierRelService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param orderCourierRel
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(OrderCourierRel orderCourierRel){
        return toAjax(orderCourierRelService.updateById(orderCourierRel));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(orderCourierRelService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(orderCourierRelService.removeByIds(ids));
    }

}

