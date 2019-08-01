package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ReadHistory;
import com.mbyte.easy.recycle.service.IReadHistoryService;
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
@RequestMapping("/recycle/readHistory")
public class ReadHistoryController extends BaseController  {

    private String prefix = "recycle/readHistory/";

    @Autowired
    private IReadHistoryService readHistoryService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param readHistory
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ReadHistory readHistory) {
        Page<ReadHistory> page = new Page<ReadHistory>(pageNo, pageSize);
        QueryWrapper<ReadHistory> queryWrapper = new QueryWrapper<ReadHistory>();
        if(!ObjectUtils.isEmpty(readHistory.getOrderId())) {
            queryWrapper = queryWrapper.like("order_id",readHistory.getOrderId());
         }
        if(!ObjectUtils.isEmpty(readHistory.getIsRead())) {
            queryWrapper = queryWrapper.like("is_read",readHistory.getIsRead());
         }
        IPage<ReadHistory> pageInfo = readHistoryService.page(page, queryWrapper);
        model.addAttribute("searchInfo", readHistory);
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
    * @param readHistory
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(ReadHistory readHistory){
        return toAjax(readHistoryService.save(readHistory));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("readHistory",readHistoryService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param readHistory
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(ReadHistory readHistory){
        return toAjax(readHistoryService.updateById(readHistory));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(readHistoryService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(readHistoryService.removeByIds(ids));
    }

}

