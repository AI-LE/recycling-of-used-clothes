package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleGuide;
import com.mbyte.easy.recycle.service.IRecycleGuideService;
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
@RequestMapping("/recycle/recycleGuide")
public class RecycleGuideController extends BaseController  {

    private String prefix = "recycle/recycleGuide/";

    @Autowired
    private IRecycleGuideService recycleGuideService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param recycleGuide
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, RecycleGuide recycleGuide) {
        Page<RecycleGuide> page = new Page<RecycleGuide>(pageNo, pageSize);
        QueryWrapper<RecycleGuide> queryWrapper = new QueryWrapper<RecycleGuide>();
        if(!ObjectUtils.isEmpty(recycleGuide.getCont())) {
            queryWrapper = queryWrapper.like("cont",recycleGuide.getCont());
         }
        if(!ObjectUtils.isEmpty(recycleGuide.getCon())) {
            queryWrapper = queryWrapper.like("con",recycleGuide.getCon());
         }
        if(!ObjectUtils.isEmpty(recycleGuide.getHiddena())) {
            queryWrapper = queryWrapper.like("hiddena",recycleGuide.getHiddena());
         }
        if(!ObjectUtils.isEmpty(recycleGuide.getDown())) {
            queryWrapper = queryWrapper.like("down",recycleGuide.getDown());
         }
        IPage<RecycleGuide> pageInfo = recycleGuideService.page(page, queryWrapper);
        model.addAttribute("searchInfo", recycleGuide);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
     * 添加跳转页面(商品订单详情)
     * @return
     */
    @GetMapping("detailBefore/{id}")
    public String detailBefore(Model model,@PathVariable("id")Long id){
        RecycleGuide recycleGuide = recycleGuideService.getById(id);
        model.addAttribute("recycleGuide",recycleGuide);
        return prefix+"details";
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
    * @param recycleGuide
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(RecycleGuide recycleGuide){
        return toAjax(recycleGuideService.save(recycleGuide));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("recycleGuide",recycleGuideService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param recycleGuide
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(RecycleGuide recycleGuide){
        return toAjax(recycleGuideService.updateById(recycleGuide));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(recycleGuideService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(recycleGuideService.removeByIds(ids));
    }

}

