package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Feature;
import com.mbyte.easy.recycle.service.IFeatureService;
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
@RequestMapping("/recycle/feature")
public class FeatureController extends BaseController  {

    private String prefix = "recycle/feature/";

    @Autowired
    private IFeatureService featureService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param feature
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Feature feature) {
        Page<Feature> page = new Page<Feature>(pageNo, pageSize);
        QueryWrapper<Feature> queryWrapper = new QueryWrapper<Feature>();
        if(!ObjectUtils.isEmpty(feature.getInfo())) {
            queryWrapper = queryWrapper.like("info",feature.getInfo());
         }
        IPage<Feature> pageInfo = featureService.page(page, queryWrapper);
        model.addAttribute("searchInfo", feature);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
     * 查看详情跳转页面
     */
    @RequestMapping("detailsBefore")
    public String detailsBefore(Model model){
        model.addAttribute(("content"),featureService.getById(0).getInfo());
        return prefix + "details";
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
    * @param feature
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Feature feature){
        return toAjax(featureService.save(feature));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("feature",featureService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param feature
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Feature feature){
        return toAjax(featureService.updateById(feature));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(featureService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(featureService.removeByIds(ids));
    }

}

