package com.mbyte.easy.rest.orderCourierRel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.OrderCourierRel;
import com.mbyte.easy.recycle.service.IOrderCourierRelService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.slf4j.Logger;
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

/**
* <p>
* 前端控制器
* </p>
* @author 艾乐
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/orderCourierRel")
public class RestOrderCourierRelController extends BaseController  {

    private static final Logger logger = LoggerFactory.getLogger(RestOrderCourierRelController.class);

    @Autowired
    private IOrderCourierRelService orderCourierRelService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param orderCourierRel
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, OrderCourierRel orderCourierRel) {
        Page<OrderCourierRel> page = new Page<OrderCourierRel>(pageNo, pageSize);
        QueryWrapper<OrderCourierRel> queryWrapper = new QueryWrapper<OrderCourierRel>();

        if(orderCourierRel.getOrderId() != null  && !"".equals(orderCourierRel.getOrderId() + "")) {
            queryWrapper = queryWrapper.like("order_id",orderCourierRel.getOrderId());
         }


        if(orderCourierRel.getCourierId() != null  && !"".equals(orderCourierRel.getCourierId() + "")) {
            queryWrapper = queryWrapper.like("courier_id",orderCourierRel.getCourierId());
         }


        if(orderCourierRel.getPickCode() != null  && !"".equals(orderCourierRel.getPickCode() + "")) {
            queryWrapper = queryWrapper.like("pick_code",orderCourierRel.getPickCode());
         }

        IPage<OrderCourierRel> pageInfo = orderCourierRelService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  orderCourierRel);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param orderCourierRel
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(OrderCourierRel orderCourierRel){
        return toAjax(orderCourierRelService.save(orderCourierRel));
    }

    /**
    * 添加
    * @param orderCourierRel
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(OrderCourierRel orderCourierRel){
        return toAjax(orderCourierRelService.updateById(orderCourierRel));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(orderCourierRelService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(orderCourierRelService.removeByIds(ids));
    }

}

