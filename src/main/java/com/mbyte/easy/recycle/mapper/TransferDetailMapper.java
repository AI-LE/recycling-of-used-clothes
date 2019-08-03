package com.mbyte.easy.recycle.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.recycle.entity.TransferDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-07-26
 */
public interface TransferDetailMapper extends BaseMapper<TransferDetail> {

    IPage<TransferDetail> selectAll(Page<TransferDetail> page);

}
