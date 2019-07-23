package com.mbyte.easy.recycle.mapper;

import com.mbyte.easy.recycle.entity.OrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-23
 */
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {
    Integer addGoods(long orderId,long goodsid);
}
