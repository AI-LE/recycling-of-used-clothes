package com.mbyte.easy.recycle.service;

import com.mbyte.easy.recycle.entity.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品类型表 服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface IGoodsTypeService extends IService<GoodsType> {
    /**
     * 查询商品分类
     * @return
     */
    List<GoodsType> selectType();

    GoodsType selectTypes();

}
