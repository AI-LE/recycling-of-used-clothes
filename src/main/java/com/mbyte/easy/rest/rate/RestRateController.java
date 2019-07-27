package com.mbyte.easy.rest.rate;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Rate;
import com.mbyte.easy.recycle.service.IRateService;
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
@RequestMapping("rest/rate")
public class RestRateController extends BaseController  {

    @Autowired
    private IRateService rateService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param rate
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Rate rate) {
        Page<Rate> page = new Page<Rate>(pageNo, pageSize);
        QueryWrapper<Rate> queryWrapper = new QueryWrapper<Rate>();

        if(rate.getWithdrawalRate() != null  && !"".equals(rate.getWithdrawalRate() + "")) {
            queryWrapper = queryWrapper.like("withdrawal_rate",rate.getWithdrawalRate());
         }


        if(rate.getPayRate() != null  && !"".equals(rate.getPayRate() + "")) {
            queryWrapper = queryWrapper.like("pay_rate",rate.getPayRate());
         }

        if(rate.getKgRate() != null  && !"".equals(rate.getKgRate() + "")) {
            queryWrapper = queryWrapper.like("kg_rate",rate.getKgRate());
        }

        IPage<Rate> pageInfo = rateService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  rate);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param rate
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(Rate rate){
        return toAjax(rateService.save(rate));
    }

    /**
    * 添加
    * @param rate
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Rate rate){
        return toAjax(rateService.updateById(rate));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(rateService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(rateService.removeByIds(ids));
    }

}

