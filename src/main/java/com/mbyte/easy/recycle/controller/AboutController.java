package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.About;
import com.mbyte.easy.recycle.service.IAboutService;
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
@RequestMapping("/recycle/about")
public class AboutController extends BaseController  {

    private String prefix = "recycle/about/";

    @Autowired
    private IAboutService aboutService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param about
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, About about) {
        Page<About> page = new Page<About>(pageNo, pageSize);
        QueryWrapper<About> queryWrapper = new QueryWrapper<About>();
        if(!ObjectUtils.isEmpty(about.getContent())) {
            queryWrapper = queryWrapper.like("content",about.getContent());
         }
        IPage<About> pageInfo = aboutService.page(page, queryWrapper);
        model.addAttribute("searchInfo", about);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
     * 查看详情跳转页面
     */
    @RequestMapping("detailsBefore")
    public String detailsBefore(Model model){
        model.addAttribute(("content"),aboutService.getById(0).getContent());
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
    * @param about
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(About about){
        return toAjax(aboutService.save(about));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("about",aboutService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param about
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(About about){
        return toAjax(aboutService.updateById(about));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(aboutService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(aboutService.removeByIds(ids));
    }

}

