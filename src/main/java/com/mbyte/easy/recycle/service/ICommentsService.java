package com.mbyte.easy.recycle.service;

import com.mbyte.easy.recycle.entity.Comments;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mbyte.easy.vo.commentsWithUser;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
public interface ICommentsService extends IService<Comments> {
    List<commentsWithUser> selectLeftJoinWeixinUser(Integer goodsId);
}
