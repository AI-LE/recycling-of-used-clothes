package com.mbyte.easy.recycle.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-19
 */
public interface GoodsMapper extends BaseMapper<Goods> {


    /**
     *
     * @return
     */
    IPage<Goods> selectAll(Page<Goods> page,String beginTime,String endTime, Goods goods);



}
