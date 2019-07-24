package com.mbyte.easy.rest.shopOrder;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.OrderGoods;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.service.*;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
 * 商品订单的增删改查
* @author 魏皓
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/shopOrder")
public class RestShopOrderController extends BaseController  {

    @Autowired
    private IShopOrderService shopOrderService;

    @Autowired
    private IOrderGoodsService orderGoodsService;

    @Autowired
    private IWeixinUserService weixinUserService;

    @Autowired
    private IUserPropService userPropService;

    @Autowired
    private IGoodsService goodsService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param shopOrder
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, String updatetimeSpace, ShopOrder shopOrder) {
        Page<ShopOrder> page = new Page<ShopOrder>(pageNo, pageSize);
        QueryWrapper<ShopOrder> queryWrapper = new QueryWrapper<ShopOrder>();

        if(shopOrder.getStatus() != null  && !"".equals(shopOrder.getStatus() + "")) {
            queryWrapper = queryWrapper.like("status",shopOrder.getStatus());
         }


        if(shopOrder.getOrderNo() != null  && !"".equals(shopOrder.getOrderNo() + "")) {
            queryWrapper = queryWrapper.like("order_no",shopOrder.getOrderNo());
         }


        if(shopOrder.getUserId() != null  && !"".equals(shopOrder.getUserId() + "")) {
            queryWrapper = queryWrapper.like("user_id",shopOrder.getUserId());
         }


        if(shopOrder.getCreatetime() != null  && !"".equals(shopOrder.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",shopOrder.getCreatetime());
         }


        if(shopOrder.getPrice() != null  && !"".equals(shopOrder.getPrice() + "")) {
            queryWrapper = queryWrapper.like("price",shopOrder.getPrice());
         }


        if(shopOrder.getAddressId() != null  && !"".equals(shopOrder.getAddressId() + "")) {
            queryWrapper = queryWrapper.like("address_id",shopOrder.getAddressId());
         }


        if(shopOrder.getPhone() != null  && !"".equals(shopOrder.getPhone() + "")) {
            queryWrapper = queryWrapper.like("phone",shopOrder.getPhone());
         }


        if(shopOrder.getExpress() != null  && !"".equals(shopOrder.getExpress() + "")) {
            queryWrapper = queryWrapper.like("express",shopOrder.getExpress());
         }


        if(shopOrder.getIsShow() != null  && !"".equals(shopOrder.getIsShow() + "")) {
            queryWrapper = queryWrapper.like("is_show",shopOrder.getIsShow());
         }


        if(shopOrder.getUpdatetime() != null  && !"".equals(shopOrder.getUpdatetime() + "")) {
            queryWrapper = queryWrapper.like("updatetime",shopOrder.getUpdatetime());
         }


        if(shopOrder.getIsDel() != null  && !"".equals(shopOrder.getIsDel() + "")) {
            queryWrapper = queryWrapper.like("id_del",shopOrder.getIsDel());
         }

        IPage<ShopOrder> pageInfo = shopOrderService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  shopOrder);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
     * 显示所有订单以及对应的所有的货物信息
     */
    @RequestMapping("viewAll")
    public AjaxResult viewAll(Long userId)
    {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",userId);
        //QueryWrapper queryWrapper1 = new QueryWrapper();
//        QueryWrapper queryWrapper2 = new QueryWrapper();
//        QueryWrapper queryWrapper3 = new QueryWrapper();
        //queryWrapper1.eq("user_id",userId);
        List<ShopOrder> shopOrders=shopOrderService.list(queryWrapper);
        for (ShopOrder shoporder:shopOrders) {
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("id",shoporder.getAddressId());
            shoporder.setAddress(userPropService.getOne(queryWrapper1).getAddress());
            QueryWrapper queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("order_id",shoporder.getId());
            List<OrderGoods> orderGoodsList=orderGoodsService.list(queryWrapper2);
            List<Goods>goodsList=new ArrayList<>();
            for (OrderGoods orderGoods:orderGoodsList
                 ) {
                QueryWrapper queryWrapper3 = new QueryWrapper();
                queryWrapper3.eq("id",orderGoods.getGoodsid());
                goodsList.add(goodsService.getOne(queryWrapper3));
            }
            shoporder.setGoodsList(goodsList);
            //shoporder.setPic(goodsList.get(0).getPic());

        }

        return this.success(shopOrders);
    }

    /**
     * 取消订单
     */
    @RequestMapping("cancel")
    public AjaxResult cancel(@RequestParam("id") Long id)
    {
        ShopOrder shopOrder=new ShopOrder();
        shopOrder.setStatus(6);
        shopOrder.setId(id);
        shopOrderService.updateById(shopOrder);
        return this.success();
    }

    /**
    * 添加
    * @param shopOrder
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(ShopOrder shopOrder){
        return toAjax(shopOrderService.save(shopOrder));
    }

    /**
    * 添加
    * @param shopOrder
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(ShopOrder shopOrder){
        return toAjax(shopOrderService.updateById(shopOrder));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(shopOrderService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(shopOrderService.removeByIds(ids));
    }

}

