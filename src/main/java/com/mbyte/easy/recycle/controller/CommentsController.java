package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Comments;
import com.mbyte.easy.recycle.service.ICommentsService;
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
@RequestMapping("/recycle/comments")
public class CommentsController extends BaseController  {

    private String prefix = "recycle/comments/";

    @Autowired
    private ICommentsService commentsService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param comments
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Comments comments) {
        Page<Comments> page = new Page<Comments>(pageNo, pageSize);
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<Comments>();
        if(!ObjectUtils.isEmpty(comments.getComment())) {
            queryWrapper = queryWrapper.like("comment",comments.getComment());
         }
        if(!ObjectUtils.isEmpty(comments.getGoodsId())) {
            queryWrapper = queryWrapper.like("goods_id",comments.getGoodsId());
         }
        if(!ObjectUtils.isEmpty(comments.getParentId())) {
            queryWrapper = queryWrapper.like("parent_id",comments.getParentId());
         }
        if(!ObjectUtils.isEmpty(comments.getUserId())) {
            queryWrapper = queryWrapper.like("user_id",comments.getUserId());
         }
        IPage<Comments> pageInfo = commentsService.page(page, queryWrapper);
        model.addAttribute("searchInfo", comments);
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
    * @param comments
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Comments comments){
        return toAjax(commentsService.save(comments));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("comments",commentsService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param comments
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Comments comments){
        return toAjax(commentsService.updateById(comments));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(commentsService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(commentsService.removeByIds(ids));
    }

}

