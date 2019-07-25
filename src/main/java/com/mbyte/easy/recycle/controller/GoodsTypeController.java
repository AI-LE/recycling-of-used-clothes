package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.GoodsType;
import com.mbyte.easy.recycle.service.IGoodsTypeService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
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
@RequestMapping("/recycle/goodsType")
public class GoodsTypeController extends BaseController  {

    private String prefix = "recycle/goodsType/";

    @Autowired
    private IGoodsTypeService goodsTypeService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param goodsType
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, GoodsType goodsType) {
        Page<GoodsType> page = new Page<GoodsType>(pageNo, pageSize);
        QueryWrapper<GoodsType> queryWrapper = new QueryWrapper<GoodsType>();
        if(!ObjectUtils.isEmpty(goodsType.getType())) {
            queryWrapper = queryWrapper.like("type",goodsType.getType());
         }
        if(!ObjectUtils.isEmpty(goodsType.getPic())) {
            queryWrapper = queryWrapper.like("pic",goodsType.getPic());
         }
        if(!ObjectUtils.isEmpty(goodsType.getIsDel())) {
            queryWrapper = queryWrapper.like("is_del",goodsType.getIsDel());
         }
        IPage<GoodsType> pageInfo = goodsTypeService.page(page, queryWrapper);
        model.addAttribute("searchInfo", goodsType);
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
    * @param goodsType
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(GoodsType goodsType){

        return toAjax(goodsTypeService.save(goodsType));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("goodsType",goodsTypeService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param goodsType
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(GoodsType goodsType){

        return toAjax(goodsTypeService.updateById(goodsType));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsTypeService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsTypeService.removeByIds(ids));
    }

}

