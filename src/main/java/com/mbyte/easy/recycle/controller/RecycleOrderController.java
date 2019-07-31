package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.entity.UserProp;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.service.IRecycleOrderService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IUserPropService;
import com.mbyte.easy.recycle.service.IWeixinUserService;
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

    @Autowired
    private IUserPropService userPropService;

    @Autowired
    private IWeixinUserService weixinUserService;

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
                                    @RequestParam(value = "phone", required = false) String phone

                                    ) {

        RecycleOrder recycleOrder = new RecycleOrder();

        if(status != null){
            recycleOrder.setStatus(status);
        }

        if(StringUtils.isNoneBlank(orderNo)){
            recycleOrder.setOrderNo(orderNo);
        }
        if(StringUtils.isNoneBlank(phone)) {
            recycleOrder.setPhone(phone);
        }

        Page<RecycleOrder> page = new Page<RecycleOrder>(pageNo, pageSize);

        IPage<RecycleOrder> pageInfo = recycleOrderService.selectAll(page,recycleOrder);
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
        List<WeixinUser> nickName = recycleOrderService.selectCouier();
        model.addAttribute("nickName",nickName);
        RecycleOrder recycleOrder = recycleOrderService.getById(id);
        model.addAttribute("recycleOrder",recycleOrder);
        return prefix+"edit";
    }

    /**
     * 添加跳转页面(商品订单详情)
     * @return
     */
    @GetMapping("detailBefore/{id}")
    public String detailBefore(Model model,@PathVariable("id")Long id){
        RecycleOrder recycleOrder = recycleOrderService.getById(id);
        UserProp userProp = userPropService.getById(recycleOrder.getAddressId());
        WeixinUser weixinUser = weixinUserService.getById(recycleOrder.getCourierId());
        model.addAttribute("weixinUser",weixinUser);
        model.addAttribute("recycleOrder",recycleOrder);
        model.addAttribute("userProp",userProp);
        return prefix+"details";
    }
    /**
    * 添加
    * @param recycleOrder
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(RecycleOrder recycleOrder) {
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

