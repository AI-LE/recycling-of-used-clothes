package com.mbyte.easy.rest.transferDetail;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.TransferDetail;
import com.mbyte.easy.recycle.service.ITransferDetailService;
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
@RequestMapping("rest/transferDetail")
public class RestTransferDetailController extends BaseController  {

    @Autowired
    private ITransferDetailService transferDetailService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param transferDetail
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, TransferDetail transferDetail) {
        Page<TransferDetail> page = new Page<TransferDetail>(pageNo, pageSize);
        QueryWrapper<TransferDetail> queryWrapper = new QueryWrapper<TransferDetail>();

        if(transferDetail.getTransferNo() != null  && !"".equals(transferDetail.getTransferNo() + "")) {
            queryWrapper = queryWrapper.like("transfer_no",transferDetail.getTransferNo());
         }


        if(transferDetail.getPrice() != null  && !"".equals(transferDetail.getPrice() + "")) {
            queryWrapper = queryWrapper.like("price",transferDetail.getPrice());
         }


        if(transferDetail.getCreatetime() != null  && !"".equals(transferDetail.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",transferDetail.getCreatetime());
         }

        IPage<TransferDetail> pageInfo = transferDetailService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  transferDetail);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }

    @RequestMapping ("add")
    public AjaxResult getTransferDetail(Long userId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        return this.success( transferDetailService.list(queryWrapper));
    }
    /**
    * 添加
    * @param transferDetail
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(TransferDetail transferDetail){
        return toAjax(transferDetailService.save(transferDetail));
    }

    /**
    * 添加
    * @param transferDetail
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(TransferDetail transferDetail){
        return toAjax(transferDetailService.updateById(transferDetail));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(transferDetailService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(transferDetailService.removeByIds(ids));
    }

}

