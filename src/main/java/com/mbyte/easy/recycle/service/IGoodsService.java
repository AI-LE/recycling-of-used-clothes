package com.mbyte.easy.recycle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-19
 */
public interface IGoodsService extends IService<Goods> {

    IPage<Goods> selectAll(Page<Goods> page, String createTime, Goods goods);

    /**
     * 商品详情
     */
    Goods selectDetail(Long id);

}
