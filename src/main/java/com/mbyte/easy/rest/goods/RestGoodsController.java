package com.mbyte.easy.rest.goods;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.GoodsType;
import com.mbyte.easy.recycle.service.IGoodsService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.recycle.service.IGoodsTypeService;
import com.mbyte.easy.recycle.service.IShopOrderService;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@RequestMapping("rest/goods")
public class RestGoodsController extends BaseController  {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsTypeService iGoodsTypeService;

    @Autowired
    private IShopOrderService ShopOrderService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param goods
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value="Order" ,defaultValue = "") String order,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, String updatetimeSpace, Goods goods) {
        Page<Goods> page = new Page<Goods>(pageNo, pageSize);
        QueryWrapper<Goods> queryWrapper = null;
        if(order.equals("")){queryWrapper = new QueryWrapper<Goods>().orderByAsc("price");}
        else{queryWrapper = new QueryWrapper<Goods>().orderByDesc(order);}

        if(goods.getName() != null  && !"".equals(goods.getName() + "")) {
            queryWrapper = queryWrapper.like("name",goods.getName());
         }


        if(goods.getPic() != null  && !"".equals(goods.getPic() + "")) {
            queryWrapper = queryWrapper.like("pic",goods.getPic());
         }


        if(goods.getPrice() != null  && !"".equals(goods.getPrice() + "")) {
            queryWrapper = queryWrapper.like("price",goods.getPrice());
         }


        if(goods.getInfo() != null  && !"".equals(goods.getInfo() + "")) {
            queryWrapper = queryWrapper.like("info",goods.getInfo());
         }


        if(goods.getGoodsTypeId() != null  && !"".equals(goods.getGoodsTypeId() + "")) {
            queryWrapper = queryWrapper.like("goods_type_id",goods.getGoodsTypeId());
         }


        if(goods.getCreatetime() != null  && !"".equals(goods.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",goods.getCreatetime());
         }


        if(goods.getUpdatetime() != null  && !"".equals(goods.getUpdatetime() + "")) {
            queryWrapper = queryWrapper.like("updatetime",goods.getUpdatetime());
         }


        if(goods.getIsDel() != null  && !"".equals(goods.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("is_del",goods.getIsDel());
         }

        IPage<Goods> pageInfo = goodsService.page(page, queryWrapper);
        List<GoodsType> goodsTypeLists=iGoodsTypeService.list();
        Map<String, Object> map = new HashMap<>();
        map.put("goodsType",goodsTypeLists);
        map.put("searchInfo",  goods);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


//    /**
//     * 搜索界面
//     * @param goods
//     * @return
//     */
//    @GetMapping("Search")
//    public AjaxResult Search(Goods goods)
//    {
//
//    }

    /**
     * 按id搜索
     * @return
     */
    @GetMapping("details")
    public AjaxResult details(@RequestParam("id")Integer id)
    {
        Goods goods=goodsService.getById(id);
        return this.success(goods);
    }




    /**
    * 添加
    * @param goods
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(Goods goods){
        return toAjax(goodsService.save(goods));
    }

    /**
    * 添加
    * @param goods
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(Goods goods){
        return toAjax(goodsService.updateById(goods));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(goodsService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(goodsService.removeByIds(ids));
    }


    /**
     * 商城订单生成
     */
    @RequestMapping("addOrder")
    public AjaxResult addOrder(String addressId, String[] goodsIds, BigDecimal totalPrice,String userId){

        for (String temp:goodsIds
             ) {
            System.err.println(temp);
        }
        long[] goodsIdList=new long[goodsIds.length];
        for(int i=0;i<goodsIds.length;i++)
        {
            goodsIdList[i]=Long.parseLong(goodsIds[i].replaceAll("\\[","").replaceAll("]",""));
        }
           if(StringUtils.isNotEmpty(userId)) {
               Long result = ShopOrderService.addOrder(addressId, goodsIdList, totalPrice, userId);
               return  this.success(result);
           }else{
               return  this.error();
           }

    }

}

