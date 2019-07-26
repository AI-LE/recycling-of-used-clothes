package com.mbyte.easy.recycle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.GoodsImg;
import com.mbyte.easy.recycle.mapper.GoodsImgMapper;
import com.mbyte.easy.recycle.service.IGoodsImgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品详情页面图片表 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-24
 */
@Service
public class GoodsImgServiceImpl extends ServiceImpl<GoodsImgMapper, GoodsImg> implements IGoodsImgService {
    @Resource

    private GoodsImgMapper goodsImgMapper;

    /**
     *
     * @param goodsImg
     * @param page
     * @return
     */
    @Override
    public IPage<GoodsImg> selectGoodName(GoodsImg goodsImg, Page<GoodsImg> page) {


        return goodsImgMapper.selectGoodName(page,goodsImg);
    }

    /**
     *查询goods名字，在goodsImg中给对应的goods添加图片
     * @return
     */
    @Override
    public List<GoodsImg> selectGoodsName() {
        List<GoodsImg> goodsImgs = goodsImgMapper.selectGoodsName();
        return goodsImgs;
    }
}
