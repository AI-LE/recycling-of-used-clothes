package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.GoodsType;
import com.mbyte.easy.recycle.mapper.GoodsTypeMapper;
import com.mbyte.easy.recycle.service.IGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品类型表 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> selectType() {
        List<GoodsType> ListType = goodsTypeMapper.selectType();
        return ListType;
    }

    @Override
    public GoodsType selectTypes() {
        return goodsTypeMapper.selectTypes();
    }
}
