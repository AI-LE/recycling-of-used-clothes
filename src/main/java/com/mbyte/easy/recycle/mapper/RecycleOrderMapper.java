package com.mbyte.easy.recycle.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mbyte.easy.recycle.entity.WeixinUser;

import java.util.List;

/**
 * <p>
 * 回收订单表 Mapper 接口
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-18
 */
public interface RecycleOrderMapper extends BaseMapper<RecycleOrder> {
    /**
     * 查询微信用户里的取货员
     * @return
     */
    List<WeixinUser>selectCouier();

    /**
     * 查询回收订单信息
     * @param page
     * @param recycleOrder
     * @return
     */
    IPage<RecycleOrder> selectAll(Page<RecycleOrder> page,  RecycleOrder recycleOrder);

}
