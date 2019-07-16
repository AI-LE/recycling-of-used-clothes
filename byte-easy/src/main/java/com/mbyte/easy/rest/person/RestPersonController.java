package com.mbyte.easy.rest.person;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Person;
import com.mbyte.easy.admin.service.IPersonService;
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
* @author 会写代码的怪叔叔
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/person")
public class RestPersonController extends BaseController  {

    @Autowired
    private IPersonService personService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param person
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String creTimeSpace, Person person) {
        Page<Person> page = new Page<Person>(pageNo, pageSize);
        QueryWrapper<Person> queryWrapper = new QueryWrapper<Person>();

        if(person.getName() != null  && !"".equals(person.getName() + "")) {
            queryWrapper = queryWrapper.like("name",person.getName());
         }


        if(person.getCreTime() != null  && !"".equals(person.getCreTime() + "")) {
            queryWrapper = queryWrapper.like("cre_time",person.getCreTime());
         }


        if(person.getFilePath() != null  && !"".equals(person.getFilePath() + "")) {
            queryWrapper = queryWrapper.like("file_path",person.getFilePath());
         }


        if(person.getDescrption() != null  && !"".equals(person.getDescrption() + "")) {
            queryWrapper = queryWrapper.like("descrption",person.getDescrption());
         }

        IPage<Person> pageInfo = personService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  person);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param person
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(Person person){
        return toAjax(personService.save(person));
    }

    /**
    * 添加
    * @param person
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Person person){
        return toAjax(personService.updateById(person));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(personService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(personService.removeByIds(ids));
    }

}

