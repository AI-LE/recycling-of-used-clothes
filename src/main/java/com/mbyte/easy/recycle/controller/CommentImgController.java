package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.CommentImg;
import com.mbyte.easy.recycle.service.ICommentImgService;
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
@RequestMapping("/recycle/commentImg")
public class CommentImgController extends BaseController  {

    private String prefix = "recycle/commentImg/";

    @Autowired
    private ICommentImgService commentImgService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param commentImg
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, CommentImg commentImg) {
        Page<CommentImg> page = new Page<CommentImg>(pageNo, pageSize);
        QueryWrapper<CommentImg> queryWrapper = new QueryWrapper<CommentImg>();
        if(!ObjectUtils.isEmpty(commentImg.getCommentid())) {
            queryWrapper = queryWrapper.like("commentid",commentImg.getCommentid());
         }
        if(!ObjectUtils.isEmpty(commentImg.getPicUrl())) {
            queryWrapper = queryWrapper.like("picUrl",commentImg.getPicUrl());
         }
        IPage<CommentImg> pageInfo = commentImgService.page(page, queryWrapper);
        model.addAttribute("searchInfo", commentImg);
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
    * @param commentImg
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(CommentImg commentImg){
        return toAjax(commentImgService.save(commentImg));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("commentImg",commentImgService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param commentImg
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(CommentImg commentImg){
        return toAjax(commentImgService.updateById(commentImg));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(commentImgService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(commentImgService.removeByIds(ids));
    }

}

