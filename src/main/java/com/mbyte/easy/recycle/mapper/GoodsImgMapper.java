package com.mbyte.easy.recycle.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.entity.GoodsImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品详情页面图片表 Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-24
 */
public interface GoodsImgMapper extends BaseMapper<GoodsImg> {

    /**
     * 查询
     * @param
     * @return
     */
     IPage<GoodsImg> selectGoodName( Page<GoodsImg> page,GoodsImg goodsImg);

    /**
     * 查询goods名字，再goodsImg中给对应的goods添加图片
     * @return
     */
    List<Goods> selectGoodsName();

}
