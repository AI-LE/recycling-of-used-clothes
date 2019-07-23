package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.service.IShopOrderService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author
* @since 2019-03-11
*/
@Controller
@RequestMapping("/recycle/shopOrder")
public class ShopOrderController extends BaseController  {

    private String prefix = "recycle/shopOrder/";

    @Autowired
    private IShopOrderService shopOrderService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,

                        @RequestParam(value = "status", required = false) Integer status,
                        @RequestParam(value = "orderNo", required = false) String orderNo,
                        @RequestParam(value = "price", required = false ) BigDecimal price,
                        @RequestParam(value = "express", required = false) String express,
                        @RequestParam(value = "phone", required = false) String phone,
                        @RequestParam(value = "createtimeSpace", required = false) String createtimeSpace
    ) {


        ShopOrder shopOrder = new ShopOrder();

//        if (name != null && !"".equals(name.trim())) {
//            good.setName(name.trim());   //搜索时候设置值
//
//        }
        if(status != null){
            shopOrder.setStatus(status);
        }

        if(StringUtils.isNoneBlank(orderNo)){
            shopOrder.setOrderNo(orderNo);
        }

        if (price != null) {
            shopOrder.setPrice(price);
        }
        if(StringUtils.isNoneBlank(phone)) {
            shopOrder.setPhone(phone);
        }
        if (StringUtils.isNoneBlank(express))
        {
            shopOrder.setExpress(express);
        }


        Page<ShopOrder> page = new Page<ShopOrder>(pageNo, pageSize);


        IPage<ShopOrder> pageInfo = shopOrderService.selectAllShopOrder(page,createtimeSpace,shopOrder);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", shopOrder);
        model.addAttribute("pageInfo", new PageInfo<ShopOrder>(pageInfo));
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
    * @param shopOrder
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(ShopOrder shopOrder){
        return toAjax(shopOrderService.save(shopOrder));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("shopOrder",shopOrderService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param shopOrder
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(ShopOrder shopOrder){
        shopOrder.setUpdatetime(LocalDateTime.now());
        return toAjax(shopOrderService.updateById(shopOrder));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(shopOrderService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(shopOrderService.removeByIds(ids));
    }

}

