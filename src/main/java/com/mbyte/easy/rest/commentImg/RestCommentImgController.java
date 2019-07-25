package com.mbyte.easy.rest.commentImg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.CommentImg;
import com.mbyte.easy.recycle.service.ICommentImgService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
* <p>
* 前端控制器
* </p>
* @author Author
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/commentImg")
public class RestCommentImgController extends BaseController  {

    @Autowired
    private ICommentImgService commentImgService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param commentImg
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, CommentImg commentImg) {
        Page<CommentImg> page = new Page<CommentImg>(pageNo, pageSize);
        QueryWrapper<CommentImg> queryWrapper = new QueryWrapper<CommentImg>();

        if(commentImg.getCommentid() != null  && !"".equals(commentImg.getCommentid() + "")) {
            queryWrapper = queryWrapper.like("commentid",commentImg.getCommentid());
         }


        if(commentImg.getPicUrl() != null  && !"".equals(commentImg.getPicUrl() + "")) {
            queryWrapper = queryWrapper.like("picUrl",commentImg.getPicUrl());
         }

        IPage<CommentImg> pageInfo = commentImgService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  commentImg);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param commentImg
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(CommentImg commentImg){
        return toAjax(commentImgService.save(commentImg));
    }

    /**
    * 添加
    * @param commentImg
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(CommentImg commentImg){
        return toAjax(commentImgService.updateById(commentImg));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(commentImgService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(commentImgService.removeByIds(ids));
    }

}

