package com.mbyte.easy.recycle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mbyte.easy.recycle.entity.WeixinUser;

import java.util.List;

/**
 * <p>
 * 回收订单表 服务类
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-18
 */
public interface IRecycleOrderService extends IService<RecycleOrder> {
    /**
     * 查询微信用户中的取货员
     * @return
     */
    List<WeixinUser> selectCouier();
    /**
     * 查询回收订单信息
     * @param page
     * @param recycleOrder
     * @return
     */
    IPage<RecycleOrder> selectAll(Page<RecycleOrder> page, RecycleOrder recycleOrder);

}
