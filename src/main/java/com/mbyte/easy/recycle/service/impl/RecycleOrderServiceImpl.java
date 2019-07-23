package com.mbyte.easy.recycle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.RecycleOrder;
import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.mapper.RecycleOrderMapper;
import com.mbyte.easy.recycle.service.IRecycleOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 回收订单表 服务实现类
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-18
 */
@Service
public class RecycleOrderServiceImpl extends ServiceImpl<RecycleOrderMapper, RecycleOrder> implements IRecycleOrderService {
    @Resource
    private RecycleOrderMapper recycleOrderMapper;



    /**
     * 查询微信用户中的取货员
     * @return
     */

    @Override
    public List<WeixinUser> selectCouier() {
        return recycleOrderMapper.selectCouier();
    }


    @Override
    public IPage<RecycleOrder> selectAll(Page<RecycleOrder> page, RecycleOrder recycleOrder) {

        return recycleOrderMapper.selectAll(page,recycleOrder);
    }
}
