
package com.mbyte.easy.recycle.service;

import com.mbyte.easy.recycle.entity.WeixinUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;


/**
 * <p>
 * 微信用户表 服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface IWeixinUserService extends IService<WeixinUser> {

    Long insertWeixinUser(WeixinUser weixinUser);

    Integer updateBalance(BigDecimal balance, Long userId);
}

