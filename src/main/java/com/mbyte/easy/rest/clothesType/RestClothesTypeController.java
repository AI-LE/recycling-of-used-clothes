package com.mbyte.easy.rest.clothesType;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ClothesType;
import com.mbyte.easy.recycle.entity.RecycleGuide;
import com.mbyte.easy.recycle.service.IClothesTypeService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IRecycleGuideService;
import com.mbyte.easy.util.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
* @author 艾乐
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/clothesType")
public class RestClothesTypeController extends BaseController  {

    private static final Logger logger = LoggerFactory.getLogger(RestClothesTypeController.class);

    @Autowired
    private IClothesTypeService clothesTypeService;

    @Autowired
    private IRecycleGuideService recycleGuideService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param clothesType
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ClothesType clothesType) {
        Page<ClothesType> page = new Page<ClothesType>(pageNo, pageSize);
        QueryWrapper<ClothesType> queryWrapper = new QueryWrapper<ClothesType>();

        if(clothesType.getType() != null  && !"".equals(clothesType.getType() + "")) {
            queryWrapper = queryWrapper.eq("type",clothesType.getType());
         }


        if(clothesType.getPic() != null  && !"".equals(clothesType.getPic() + "")) {
            queryWrapper = queryWrapper.eq("pic",clothesType.getPic());
         }


        queryWrapper = queryWrapper.eq("is_del","1");

        IPage<ClothesType> pageInfo = clothesTypeService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  clothesType);
        map.put("pageInfo", new PageInfo(pageInfo));

        List<RecycleGuide> list = recycleGuideService.list();
        map.put("List", list);

        return this.success(map);
    }


    /**
    * 添加
    * @param clothesType
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(ClothesType clothesType){
        clothesType.setIsDel(2);
        return toAjax(clothesTypeService.save(clothesType));
    }

    /**
    * 编辑
    * @param clothesType
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(ClothesType clothesType){
        return toAjax(clothesTypeService.updateById(clothesType));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(clothesTypeService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(clothesTypeService.removeByIds(ids));
    }

}

