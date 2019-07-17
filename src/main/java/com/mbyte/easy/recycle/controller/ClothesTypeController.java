package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ClothesType;
import com.mbyte.easy.recycle.service.IClothesTypeService;
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
@RequestMapping("/recycle/clothesType")
public class ClothesTypeController extends BaseController  {

    private String prefix = "recycle/clothesType/";

    @Autowired
    private IClothesTypeService clothesTypeService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param clothesType
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ClothesType clothesType) {
        Page<ClothesType> page = new Page<ClothesType>(pageNo, pageSize);
        QueryWrapper<ClothesType> queryWrapper = new QueryWrapper<ClothesType>();
        if(!ObjectUtils.isEmpty(clothesType.getType())) {
            queryWrapper = queryWrapper.like("type",clothesType.getType());
         }
        if(!ObjectUtils.isEmpty(clothesType.getPic())) {
            queryWrapper = queryWrapper.like("pic",clothesType.getPic());
         }
        if(!ObjectUtils.isEmpty(clothesType.getIsDel())) {
            queryWrapper = queryWrapper.like("is_del",clothesType.getIsDel());
         }
        IPage<ClothesType> pageInfo = clothesTypeService.page(page, queryWrapper);
        model.addAttribute("searchInfo", clothesType);
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
    * @param clothesType
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(ClothesType clothesType){
        return toAjax(clothesTypeService.save(clothesType));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("clothesType",clothesTypeService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param clothesType
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(ClothesType clothesType){
        return toAjax(clothesTypeService.updateById(clothesType));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(clothesTypeService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(clothesTypeService.removeByIds(ids));
    }

}

