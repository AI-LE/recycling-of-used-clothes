package com.mbyte.easy.recycle.service.impl;

import com.mbyte.easy.recycle.entity.Comments;
import com.mbyte.easy.recycle.mapper.CommentsMapper;
import com.mbyte.easy.recycle.service.ICommentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mbyte.easy.vo.commentsWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;
    public List<commentsWithUser> selectLeftJoinWeixinUser(Integer goodsId){return commentsMapper.selectLeftJoinWeixinUser(goodsId);}

}
