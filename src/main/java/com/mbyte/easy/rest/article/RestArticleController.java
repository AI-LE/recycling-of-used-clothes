package com.mbyte.easy.rest.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Article;
import com.mbyte.easy.recycle.service.IArticleService;
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
@RequestMapping("rest/article")
public class RestArticleController extends BaseController  {

    @Autowired
    private IArticleService articleService;

    /**
    * 查询列表
    *
    * @return
    */
    @RequestMapping
    public AjaxResult index() {

        Map<String,List<Article>> map = new HashMap<>();
        map.put("article", articleService.list());

        return this.success(map);
    }

    /**
     * 根据id值取得内容
     */
    @RequestMapping("getContent")
    @ResponseBody
    public AjaxResult getContent(@RequestParam("id") Long id){
        Article article = articleService.getById(id);
        Map<String,String> map = new HashMap<>();
        map.put("content",article.getContent());
        return success(map);
    }


    /**
    * 添加
    * @param article
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(Article article){
        return toAjax(articleService.save(article));
    }

    /**
    * 添加
    * @param article
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Article article){
        return toAjax(articleService.updateById(article));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(articleService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(articleService.removeByIds(ids));
    }

}

