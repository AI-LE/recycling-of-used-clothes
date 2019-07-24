package com.mbyte.easy.rest.comments;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Comments;
import com.mbyte.easy.recycle.service.ICommentsService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import com.mbyte.easy.vo.commentsWithUser;
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
* @author 魏皓
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/comments")
public class RestCommentsController extends BaseController  {

    @Autowired
    private ICommentsService commentsService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param comments
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "1000") Integer pageSize, String createtimeSpace, Comments comments) {
        Page<Comments> page = new Page<Comments>(pageNo, pageSize);
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<Comments>();

        if(comments.getGoodsId() != null  && !"".equals(comments.getGoodsId() + "")) {
            queryWrapper = queryWrapper.like("goods_id",comments.getGoodsId());
         }


        if(comments.getParentId() != null  && !"".equals(comments.getParentId() + "")) {
            queryWrapper = queryWrapper.like("parent_id",comments.getParentId());
         }


        if(comments.getUserId() != null  && !"".equals(comments.getUserId() + "")) {
            queryWrapper = queryWrapper.like("user_id",comments.getUserId());
         }


        if(comments.getComment() != null  && !"".equals(comments.getComment() + "")) {
            queryWrapper = queryWrapper.like("comment",comments.getComment());
         }


        if(comments.getCreatetime() != null  && !"".equals(comments.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",comments.getCreatetime());
         }

        IPage<Comments> pageInfo = commentsService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  comments);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }

    /**
     * 查询所有带User的评论
     */
    @GetMapping("select")
    public AjaxResult select(@RequestParam("goodsid")Integer goodsId)
    {
        List<commentsWithUser> commentsWithUserLists=commentsService.selectLeftJoinWeixinUser(goodsId);
        return this.success(commentsWithUserLists);
    }


    /**
     * 添加评论
     */
//    public AjaxResult

    /**
     * 添加
    * @param comments
    * @return
    */
    @RequestMapping("add")
    public AjaxResult add(Comments comments ,Boolean hideUsername,String comment_tag){
        System.err.println(comment_tag);
        if(!hideUsername)
        {
            comments.setCreatetime(LocalDateTime.now());
            //comments.setUserId(new Long(0));
        }
        else
        {
            comments.setCreatetime(LocalDateTime.now());
            comments.setUserId(new Long(99999));
        }
        return toAjax(commentsService.save(comments));
    }

    /**
    * 添加
    * @param comments
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Comments comments){
        return toAjax(commentsService.updateById(comments));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(commentsService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(commentsService.removeByIds(ids));
    }

}

