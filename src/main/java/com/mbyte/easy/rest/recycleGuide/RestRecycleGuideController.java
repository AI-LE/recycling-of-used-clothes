package com.mbyte.easy.rest.recycleGuide;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleGuide;
import com.mbyte.easy.recycle.service.IRecycleGuideService;
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
@RequestMapping("rest/recycleGuide")
public class RestRecycleGuideController extends BaseController  {

    @Autowired
    private IRecycleGuideService recycleGuideService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param recycleGuide
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, RecycleGuide recycleGuide) {
        Page<RecycleGuide> page = new Page<RecycleGuide>(pageNo, pageSize);
        QueryWrapper<RecycleGuide> queryWrapper = new QueryWrapper<RecycleGuide>();

        if(recycleGuide.getCont() != null  && !"".equals(recycleGuide.getCont() + "")) {
            queryWrapper = queryWrapper.like("cont",recycleGuide.getCont());
         }


        if(recycleGuide.getCon() != null  && !"".equals(recycleGuide.getCon() + "")) {
            queryWrapper = queryWrapper.like("con",recycleGuide.getCon());
         }


        if(recycleGuide.getHiddena() != null  && !"".equals(recycleGuide.getHiddena() + "")) {
            queryWrapper = queryWrapper.like("hiddena",recycleGuide.getHiddena());
         }


        if(recycleGuide.getDown() != null  && !"".equals(recycleGuide.getDown() + "")) {
            queryWrapper = queryWrapper.like("down",recycleGuide.getDown());
         }

        IPage<RecycleGuide> pageInfo = recycleGuideService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  recycleGuide);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param recycleGuide
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(RecycleGuide recycleGuide){
        return toAjax(recycleGuideService.save(recycleGuide));
    }

    /**
    * 添加
    * @param recycleGuide
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(RecycleGuide recycleGuide){
        return toAjax(recycleGuideService.updateById(recycleGuide));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(recycleGuideService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(recycleGuideService.removeByIds(ids));
    }

}

