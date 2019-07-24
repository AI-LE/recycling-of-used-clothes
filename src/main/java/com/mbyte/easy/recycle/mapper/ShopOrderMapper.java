
package com.mbyte.easy.recycle.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品订单 Mapper 接口
 * </p>
 *
 * @author
 * @since 2019-07-22
 */
public interface ShopOrderMapper extends BaseMapper<ShopOrder> {

    IPage<ShopOrder> selectAllShopOrder(Page<ShopOrder> page,String beginTime,String endTime, ShopOrder shopOrders);

}

