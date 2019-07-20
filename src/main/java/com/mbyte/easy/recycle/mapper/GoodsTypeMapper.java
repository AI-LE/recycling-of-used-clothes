package com.mbyte.easy.recycle.mapper;

import com.mbyte.easy.recycle.entity.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品类型表 Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    /**
     * 查询商品的分类
     * @return
     */

    List<GoodsType> selectType();

    GoodsType selectTypes();

}
