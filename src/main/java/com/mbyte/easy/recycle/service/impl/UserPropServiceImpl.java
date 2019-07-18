package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.UserProp;
import com.mbyte.easy.recycle.mapper.UserPropMapper;
import com.mbyte.easy.recycle.service.IUserPropService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户属性表 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
@Service
public class UserPropServiceImpl extends ServiceImpl<UserPropMapper, UserProp> implements IUserPropService {
    @Autowired
    private  UserPropMapper userPropMapper;

}
