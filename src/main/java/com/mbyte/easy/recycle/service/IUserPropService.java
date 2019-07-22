package com.mbyte.easy.recycle.service;

import com.mbyte.easy.recycle.entity.UserProp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户属性表 服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface IUserPropService extends IService<UserProp> {

    int insert(UserProp userProp);

}
