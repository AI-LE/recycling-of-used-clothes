package com.mbyte.easy.recycle.mapper;

import com.mbyte.easy.recycle.entity.WeixinUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 微信用户表 Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface WeixinUserMapper extends BaseMapper<WeixinUser> {

    Long insertWeixinUser( String openId);
}
