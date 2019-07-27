package com.mbyte.easy.recycle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.OrderGoods;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.entity.UserProp;
import com.mbyte.easy.recycle.mapper.OrderGoodsMapper;
import com.mbyte.easy.recycle.mapper.ShopOrderMapper;
import com.mbyte.easy.recycle.service.IShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    public IPage<ShopOrder> selectAllShopOrder(Page<ShopOrder> page, String createTime, ShopOrder shopOrders) {
        String beginTime = null;
        String endTime = null;

        if(createTime != null && !"".equals(createTime)){
            beginTime = createTime.split(" - ")[0];
            endTime = createTime.split(" - ")[1];
        }
        return shopOrderMapper.selectAllShopOrder(page,beginTime,endTime,shopOrders);

    }
    @Override
    public Long addOrder(String addressId, long[] goodsIds ,long[] buyNums, BigDecimal totalPrice, String userId,String payStyle) {
        //商户订单号(时间戳+随机数)
        int r = (int) ((Math.random() * 9 + 1) * 100000);
        String orderNo = System.currentTimeMillis() + String.valueOf(r);
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setCreatetime(LocalDateTime.now());
        shopOrder.setUpdatetime(LocalDateTime.now());
        shopOrder.setAddressId(Long.parseLong(addressId));
        shopOrder.setUserId(Long.parseLong(userId));
        shopOrder.setPrice(totalPrice);
        shopOrder.setOrderNo(orderNo);
        shopOrder.setPayStyle(payStyle);
        shopOrderMapper.addOrder(shopOrder);

        for(int i=0;i<goodsIds.length;i++)
        {
            orderGoodsMapper.addGoods(shopOrder.getId(),goodsIds[i],buyNums[i]);
        }

//        for (long goodsId : goodsIds) {
//            orderGoodsMapper.addGoods(shopOrder.getId(), goodsId);
//        }
        return shopOrder.getId();


    }
    /**
     * 查询商城订单货物
     * @param id
     * @return
     */
    @Override
    public List<Goods> selectGoodsOrder(Long id) {

        return shopOrderMapper.selectGoodsOrder(id);
    }

//    /**
//     * 回显
//     * @param
//     * @return
////     */
//    @Override
//    public List<UserProp> selectHuiXian(Long addressId) {
//        return shopOrderMapper.selectHuiXian(addressId);
//    }

}
