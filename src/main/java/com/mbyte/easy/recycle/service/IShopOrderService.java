
package com.mbyte.easy.recycle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商品订单 服务类
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-22
 */
public interface IShopOrderService extends IService<ShopOrder> {

    IPage<ShopOrder> selectAllShopOrder(Page<ShopOrder> page, String createTime, ShopOrder shopOrders);

    Long addOrder(String addressId, long[] goodsIds, long[] buyNums,BigDecimal totalPrice, String userId);

    /**
     * 查询商城订单货物
     * @param id
     * @return
     */
    List<Goods> selectGoodsOrder(Long id);

}

