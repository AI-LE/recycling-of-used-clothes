package com.mbyte.easy.rest.readHistory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ReadHistory;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.mapper.RecycleOrderMapper;
import com.mbyte.easy.recycle.mapper.ShopOrderMapper;
import com.mbyte.easy.recycle.service.IReadHistoryService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IShopOrderService;
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
@RequestMapping("rest/readHistory")
public class RestReadHistoryController extends BaseController  {

    @Autowired
    private IReadHistoryService readHistoryService;

    @Autowired
    private RecycleOrderMapper recycleOrderMapper;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param readHistory
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ReadHistory readHistory) {
        Page<ReadHistory> page = new Page<ReadHistory>(pageNo, pageSize);
        QueryWrapper<ReadHistory> queryWrapper = new QueryWrapper<ReadHistory>();

        if(readHistory.getOrderId() != null  && !"".equals(readHistory.getOrderId() + "")) {
            queryWrapper = queryWrapper.like("order_id",readHistory.getOrderId());
         }


        if(readHistory.getIsRead() != null  && !"".equals(readHistory.getIsRead() + "")) {
            queryWrapper = queryWrapper.like("is_read",readHistory.getIsRead());
         }

        IPage<ReadHistory> pageInfo = readHistoryService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  readHistory);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }
    /**
     * 查询
     * @param id
     * @return
     */
    @PostMapping("selectHistory")
    public AjaxResult selectHistory(Long id){
        List<ShopOrder> list = recycleOrderMapper.selectHistory(id);
        return  this.success(list);

    }


    /**
    * 添加
    * @param readHistory
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(ReadHistory readHistory){
        return toAjax(readHistoryService.save(readHistory));
    }

    /**
    * 添加
    * @param readHistory
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(ReadHistory readHistory){

        return toAjax(readHistoryService.updateById(readHistory));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(readHistoryService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(readHistoryService.removeByIds(ids));
    }

}

