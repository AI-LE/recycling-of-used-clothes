

package com.mbyte.easy.recycle.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * 商品订单 Mapper 接口
 * </p>
 *
 * @author
 * @since 2019-07-22
 */
public interface ShopOrderMapper extends BaseMapper<ShopOrder> {
    /**
     * 查询所有商城订单
     * @param page
     * @param beginTime
     * @param endTime
     * @param shopOrders
     * @return
     */
    IPage<ShopOrder> selectAllShopOrder(Page<ShopOrder> page,String beginTime,String endTime, ShopOrder shopOrders);

    /**
     * 查询商城订单货物
     * @param id
     * @return
     */
    List<Goods> selectGoodsOrder(Long id);

}


