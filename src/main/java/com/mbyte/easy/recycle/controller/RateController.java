package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Rate;
import com.mbyte.easy.recycle.service.IRateService;
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
* @author Author
* @since 2019-03-11
*/
@Controller
@RequestMapping("/recycle/rate")
public class RateController extends BaseController  {

    private String prefix = "recycle/rate/";

    @Autowired
    private IRateService rateService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param rate
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Rate rate) {
        Page<Rate> page = new Page<Rate>(pageNo, pageSize);
        QueryWrapper<Rate> queryWrapper = new QueryWrapper<Rate>();
        if(!ObjectUtils.isEmpty(rate.getWithdrawalRate())) {
            queryWrapper = queryWrapper.like("withdrawal_rate",rate.getWithdrawalRate());
         }
        if(!ObjectUtils.isEmpty(rate.getPayRate())) {
            queryWrapper = queryWrapper.like("pay_rate",rate.getPayRate());
         }
        IPage<Rate> pageInfo = rateService.page(page, queryWrapper);
        model.addAttribute("searchInfo", rate);
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
    * @param rate
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Rate rate){
        return toAjax(rateService.save(rate));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("rate",rateService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param rate
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Rate rate){
        return toAjax(rateService.updateById(rate));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(rateService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(rateService.removeByIds(ids));
    }

}

