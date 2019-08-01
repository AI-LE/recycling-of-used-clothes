package com.mbyte.easy.rest.feature;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Feature;
import com.mbyte.easy.recycle.service.IFeatureService;
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
@RequestMapping("rest/feature")
public class RestFeatureController extends BaseController  {

    @Autowired
    private IFeatureService featureService;

    /**
    * 查询列表
    *
    * @return
    */
    @RequestMapping
    public AjaxResult index() {
        Feature feature = featureService.getById(0);    //数据库只有一条信息，且只允许一条消息
        Map<String, Object> map = new HashMap<>();
        map.put("info",  feature.getInfo());
        return this.success(map);
    }


//    /**
//    * 添加
//    * @param feature
//    * @return
//    */
//    @PostMapping("add")
//    public AjaxResult add(Feature feature){
//        return toAjax(featureService.save(feature));
//    }
//
//    /**
//    * 添加
//    * @param feature
//    * @return
//    */
//    @PostMapping("edit")
//    public AjaxResult edit(Feature feature){
//        return toAjax(featureService.updateById(feature));
//    }
//    /**
//    * 删除
//    * @param id
//    * @return
//    */
//    @GetMapping("delete/{id}")
//    public AjaxResult delete(@PathVariable("id") Long id){
//        return toAjax(featureService.removeById(id));
//    }
//    /**
//    * 批量删除
//    * @param ids
//    * @return
//    */
//    @PostMapping("deleteAll")
//    public AjaxResult deleteAll(@RequestBody List<Long> ids){
//        return toAjax(featureService.removeByIds(ids));
//    }

}

