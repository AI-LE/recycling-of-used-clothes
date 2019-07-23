package com.mbyte.easy.recycle.service;

import com.mbyte.easy.recycle.entity.ShopOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 商品订单 服务类
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
public interface IShopOrderService extends IService<ShopOrder> {

    Long addOrder(String addressId, long[] goodsIds, BigDecimal totalPrice, String userId);

}
