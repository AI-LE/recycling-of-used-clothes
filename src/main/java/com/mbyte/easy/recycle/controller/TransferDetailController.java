package com.mbyte.easy.recycle.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.TransferDetail;
import com.mbyte.easy.recycle.service.ITransferDetailService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IWeixinUserService;
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
@RequestMapping("/recycle/transferDetail")
public class TransferDetailController extends BaseController  {

    private String prefix = "recycle/transferDetail/";

    @Autowired
    private ITransferDetailService transferDetailService;

    @Autowired
    private IWeixinUserService weixinUserService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param transferDetail
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, TransferDetail transferDetail) {
        Page<TransferDetail> page = new Page<TransferDetail>(pageNo, pageSize);
        QueryWrapper<TransferDetail> queryWrapper = new QueryWrapper<TransferDetail>();
        if(!ObjectUtils.isEmpty(transferDetail.getTransferNo())) {
            queryWrapper = queryWrapper.like("transfer_no",transferDetail.getTransferNo());
         }
        if(!ObjectUtils.isEmpty(transferDetail.getPrice())) {
            queryWrapper = queryWrapper.like("price",transferDetail.getPrice());
         }
        if(!ObjectUtils.isEmpty(transferDetail.getCreatetime())) {
            queryWrapper = queryWrapper.like("createtime",transferDetail.getCreatetime());
         }
        IPage<TransferDetail> pageInfo = transferDetailService.page(page, queryWrapper);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", transferDetail);
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
    * @param transferDetail
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TransferDetail transferDetail){
        return toAjax(transferDetailService.save(transferDetail));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("transferDetail",transferDetailService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param transferDetail
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TransferDetail transferDetail){
        return toAjax(transferDetailService.updateById(transferDetail));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(transferDetailService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(transferDetailService.removeByIds(ids));
    }

}

