package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.OrderGoods;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.mapper.OrderGoodsMapper;
import com.mbyte.easy.recycle.mapper.ShopOrderMapper;
import com.mbyte.easy.recycle.service.IShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品订单 服务实现类
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
@Service
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrder> implements IShopOrderService {
    @Resource
    private ShopOrderMapper shopOrderMapper;
    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public Long addOrder(String addressId, long[] goodsIds, BigDecimal totalPrice, String userId){
        //商户订单号(时间戳+随机数)
        int r = (int) ((Math.random() * 9 + 1) * 100000);
        String orderNo  = System.currentTimeMillis()+String.valueOf(r);
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setCreatetime(LocalDateTime.now());
        shopOrder.setUpdatetime(LocalDateTime.now());
        shopOrder.setAddressId(Long.parseLong(addressId));
        shopOrder.setUserId(Long.parseLong(userId));
        shopOrder.setPrice(totalPrice);
        shopOrder.setOrderNo(orderNo);
        shopOrderMapper.addOrder(shopOrder);

        for (long goodsId:goodsIds) {
            orderGoodsMapper.addGoods(shopOrder.getId(),goodsId);
        }
        return  shopOrder.getId();


    }
}
