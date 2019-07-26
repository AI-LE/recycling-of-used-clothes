package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.GoodsImg;
import com.mbyte.easy.recycle.service.IGoodsImgService;
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
@RequestMapping("/recycle/goodsImg")
public class GoodsImgController extends BaseController  {

    private String prefix = "recycle/goodsImg/";

    @Autowired
    private IGoodsImgService goodsImgService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param goodsImg
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, GoodsImg goodsImg) {
        Page<GoodsImg> page = new Page<GoodsImg>(pageNo, pageSize);
        QueryWrapper<GoodsImg> queryWrapper = new QueryWrapper<GoodsImg>();
        if(!ObjectUtils.isEmpty(goodsImg.getGoodsId())) {
            queryWrapper = queryWrapper.like("goods_id",goodsImg.getGoodsId());
         }
        if(!ObjectUtils.isEmpty(goodsImg.getPic())) {
            queryWrapper = queryWrapper.like("pic",goodsImg.getPic());
         }
        if(!ObjectUtils.isEmpty(goodsImg.getIsDel())) {
            queryWrapper = queryWrapper.like("is_del",goodsImg.getIsDel());
         }
        IPage<GoodsImg> pageInfo = goodsImgService.page(page, queryWrapper);
        model.addAttribute("searchInfo", goodsImg);
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
    * @param goodsImg
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(GoodsImg goodsImg){
        return toAjax(goodsImgService.save(goodsImg));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("goodsImg",goodsImgService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param goodsImg
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(GoodsImg goodsImg){
        return toAjax(goodsImgService.updateById(goodsImg));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsImgService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsImgService.removeByIds(ids));
    }

}

