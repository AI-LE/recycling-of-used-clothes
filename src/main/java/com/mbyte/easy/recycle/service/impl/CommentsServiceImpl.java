package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.Comments;
import com.mbyte.easy.recycle.mapper.CommentsMapper;
import com.mbyte.easy.recycle.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-07-17
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

}
