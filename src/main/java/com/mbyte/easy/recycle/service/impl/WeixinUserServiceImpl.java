package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.mapper.WeixinUserMapper;
import com.mbyte.easy.recycle.service.IWeixinUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 微信用户表 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
@Service
public class WeixinUserServiceImpl extends ServiceImpl<WeixinUserMapper, WeixinUser> implements IWeixinUserService {

    @Autowired
    WeixinUserMapper weixinUserMapper;

    @Override
    @Transactional
    public Long insertWeixinUser(WeixinUser weixinUser) {
        return weixinUserMapper.insertWeixinUser(weixinUser.getOpenId());
    }
}
