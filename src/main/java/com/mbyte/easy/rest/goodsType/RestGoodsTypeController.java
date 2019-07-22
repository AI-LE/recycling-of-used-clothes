package com.mbyte.easy.rest.goodsType;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.GoodsType;
import com.mbyte.easy.recycle.service.IGoodsTypeService;
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
 *
 *商品类型表的增删改查
* @author 魏皓
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/goodsType")
public class RestGoodsTypeController extends BaseController  {

    @Autowired
    private IGoodsTypeService goodsTypeService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param goodsType
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, GoodsType goodsType) {
        Page<GoodsType> page = new Page<GoodsType>(pageNo, pageSize);
        QueryWrapper<GoodsType> queryWrapper = new QueryWrapper<GoodsType>();

        if(goodsType.getType() != null  && !"".equals(goodsType.getType() + "")) {
            queryWrapper = queryWrapper.like("type",goodsType.getType());
         }


        if(goodsType.getPic() != null  && !"".equals(goodsType.getPic() + "")) {
            queryWrapper = queryWrapper.like("pic",goodsType.getPic());
         }


        if(goodsType.getIsDel() != null  && !"".equals(goodsType.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("is_del",goodsType.getIsDel());
         }

        IPage<GoodsType> pageInfo = goodsTypeService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  goodsType);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param goodsType
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(GoodsType goodsType){
        return toAjax(goodsTypeService.save(goodsType));
    }

    /**
    * 添加
    * @param goodsType
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(GoodsType goodsType){
        return toAjax(goodsTypeService.updateById(goodsType));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsTypeService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsTypeService.removeByIds(ids));
    }

}

