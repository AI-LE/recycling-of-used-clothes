package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Article;
import com.mbyte.easy.recycle.service.IArticleService;
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
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
* <p>
* 前端控制器
* </p>
* @author Author
* @since 2019-03-11
*/
@Controller
@RequestMapping("/recycle/article")
public class ArticleController extends BaseController  {

    private String prefix = "recycle/article/";

    @Autowired
    private IArticleService articleService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param article
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Article article) {
        Page<Article> page = new Page<Article>(pageNo, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<Article>();
        if(!ObjectUtils.isEmpty(article.getTitle())) {
            queryWrapper = queryWrapper.like("title",article.getTitle());
         }
        if(!ObjectUtils.isEmpty(article.getContent())) {
            queryWrapper = queryWrapper.like("content",article.getContent());
         }
        if(!ObjectUtils.isEmpty(article.getVicetitle())) {
            queryWrapper = queryWrapper.like("vicetitle",article.getVicetitle());
         }
        if(!ObjectUtils.isEmpty(article.getPic())) {
            queryWrapper = queryWrapper.like("pic",article.getPic());
         }
        IPage<Article> pageInfo = articleService.page(page, queryWrapper);
        model.addAttribute("searchInfo", article);
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
    * @param article
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Article article, @RequestParam("file") MultipartFile file){
        article.setPic("../images/" + FileUtil.uploadFile(file) );
        return toAjax(articleService.save(article));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("article",articleService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param article
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Article article, @RequestParam(value = "file",required = false) MultipartFile file){
        if(file != null){
            article.setPic("../images/" + FileUtil.uploadFile(file) );
        }
        return toAjax(articleService.updateById(article));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(articleService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(articleService.removeByIds(ids));
    }

}

