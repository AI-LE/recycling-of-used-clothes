
package com.mbyte.easy.recycle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.ShopOrder;
import com.mbyte.easy.recycle.mapper.ShopOrderMapper;
import com.mbyte.easy.recycle.service.IShopOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品订单 服务实现类
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-22
 */
@Service
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrder> implements IShopOrderService {
    @Resource
    private ShopOrderMapper shopOrderMapper;

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



}

