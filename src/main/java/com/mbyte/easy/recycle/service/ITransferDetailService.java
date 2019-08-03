package com.mbyte.easy.recycle.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.TransferDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-26
 */
public interface ITransferDetailService extends IService<TransferDetail> {
    IPage<TransferDetail> selectAll(Page<TransferDetail> page);

}
