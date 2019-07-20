package com.mbyte.easy.recycle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.Goods;
import com.mbyte.easy.recycle.mapper.GoodsMapper;
import com.mbyte.easy.recycle.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-19
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    /**
     *
     * @return
     */

    @Override
    public IPage<Goods> selectAll(Page<Goods> page, String createTime, Goods goods){
        String beginTime = null;
        String endTime = null;
        if(createTime != null && !"".equals(createTime)){
            beginTime = createTime.split(" - ")[0];
            endTime = createTime.split(" - ")[1];
        }
        return goodsMapper.selectAll(page,beginTime,endTime,goods);
    }
}
