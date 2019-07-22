package com.mbyte.easy.recycle.mapper;

import com.mbyte.easy.recycle.entity.UserProp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户属性表 Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface UserPropMapper extends BaseMapper<UserProp> {

   List<UserProp> getAddressList(Long userId);

   int insert(UserProp userProp);

}
