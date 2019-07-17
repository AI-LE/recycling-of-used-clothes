package com.mbyte.easy.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbyte.easy.security.entity.SysResource;
import com.mbyte.easy.security.mapper.SysResourceMapper;
import com.mbyte.easy.security.service.IResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ResourceServiceImpl
 * @Description: 系统资源服务实现类
 * @Author: lxt
 * @Date: 2019-06-10 11:38
 * @Version 1.0
 **/
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements IResourceService{

    @Override
    public List<SysResource> selectByUsername(String loginUserName) {
        return this.baseMapper.selectByUsername(loginUserName);
    }

    @Override
    public List<SysResource> selectResourceByRoleId(Long id) {
        return this.baseMapper.selectResourceByRoleId(id);
    }

    @Override
    public IPage<SysResource> listPage(SysResource sysResource, IPage<SysResource> page) {
        return page.setRecords(this.baseMapper.listPage(sysResource,page));
    }


}
