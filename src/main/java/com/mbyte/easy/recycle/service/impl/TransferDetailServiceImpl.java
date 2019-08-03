package com.mbyte.easy.recycle.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.TransferDetail;
import com.mbyte.easy.recycle.mapper.TransferDetailMapper;
import com.mbyte.easy.recycle.service.ITransferDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-26
 */
@Service
public class TransferDetailServiceImpl extends ServiceImpl<TransferDetailMapper, TransferDetail> implements ITransferDetailService {


    @Autowired
    TransferDetailMapper transferDetailMapper;

    public IPage<TransferDetail> selectAll(Page<TransferDetail> page){return transferDetailMapper.selectAll(page);}
}
