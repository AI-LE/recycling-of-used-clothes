
package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.WeixinUser;
import com.mbyte.easy.recycle.mapper.WeixinUserMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbyte.easy.recycle.service.IWeixinUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


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
        String s = weixinUserMapper.insertWeixinUser(weixinUser.getOpenId());
        System.out.println(">>>>>>>>>>>>>>"+s);
        return 1L;
    }

    public Integer updateBalance(BigDecimal balance, Long userId){
        return weixinUserMapper.updateBalance(balance,userId);
    }
}

