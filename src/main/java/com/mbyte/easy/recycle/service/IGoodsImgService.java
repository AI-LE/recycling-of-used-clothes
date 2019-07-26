package com.mbyte.easy.recycle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.GoodsImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品详情页面图片表 服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-24
 */
public interface IGoodsImgService extends IService<GoodsImg> {


    /**
     * 查询
     * @param
     * @return
     */
     IPage<GoodsImg> selectGoodName(GoodsImg goodsImg, Page<GoodsImg> page);

    /**
     * 查询goods名字，在goodsImg中给对应的goods添加图片
     * @return
     */
    List<GoodsImg> selectGoodsName();


}
