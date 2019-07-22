package com.mbyte.easy.rest.shopCar;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ShopCar;
import com.mbyte.easy.recycle.service.IShopCarService;
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
* @author 魏皓
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/shopCar")
public class RestShopCarController extends BaseController  {

    @Autowired
    private IShopCarService shopCarService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param shopCar
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, ShopCar shopCar) {
        Page<ShopCar> page = new Page<ShopCar>(pageNo, pageSize);
        QueryWrapper<ShopCar> queryWrapper = new QueryWrapper<ShopCar>();

        if(shopCar.getGoodsId() != null  && !"".equals(shopCar.getGoodsId() + "")) {
            queryWrapper = queryWrapper.like("goods_id",shopCar.getGoodsId());
         }


        if(shopCar.getUserId() != null  && !"".equals(shopCar.getUserId() + "")) {
            queryWrapper = queryWrapper.like("user_id",shopCar.getUserId());
         }


        if(shopCar.getIsDel() != null  && !"".equals(shopCar.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("is_del",shopCar.getIsDel());
         }

        IPage<ShopCar> pageInfo = shopCarService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  shopCar);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param shopCar
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(ShopCar shopCar){
        return toAjax(shopCarService.save(shopCar));
    }

    /**
    * 添加
    * @param shopCar
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(ShopCar shopCar){
        return toAjax(shopCarService.updateById(shopCar));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(shopCarService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(shopCarService.removeByIds(ids));
    }

}

