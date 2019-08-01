package com.mbyte.easy.rest.about;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.About;
import com.mbyte.easy.recycle.service.IAboutService;
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
@RequestMapping("rest/about")
public class RestAboutController extends BaseController  {

    @Autowired
    private IAboutService aboutService;

    /**
    * 查询列表
    *
    * @return
    */
    @RequestMapping
    public AjaxResult index() {

        About about = aboutService.getById(0);  //数据库只有一条数据
        Map map = new HashMap();
        map.put("content",about.getContent());
        return this.success(map);
    }


//    /**
//    * 添加
//    * @param about
//    * @return
//    */
//    @PostMapping("add")
//    public AjaxResult add(About about){
//        return toAjax(aboutService.save(about));
//    }
//
//    /**
//    * 添加
//    * @param about
//    * @return
//    */
//    @PostMapping("edit")
//    public AjaxResult edit(About about){
//        return toAjax(aboutService.updateById(about));
//    }
//    /**
//    * 删除
//    * @param id
//    * @return
//    */
//    @GetMapping("delete/{id}")
//    public AjaxResult delete(@PathVariable("id") Long id){
//        return toAjax(aboutService.removeById(id));
//    }
//    /**
//    * 批量删除
//    * @param ids
//    * @return
//    */
//    @PostMapping("deleteAll")
//    public AjaxResult deleteAll(@RequestBody List<Long> ids){
//        return toAjax(aboutService.removeByIds(ids));
//    }

}

