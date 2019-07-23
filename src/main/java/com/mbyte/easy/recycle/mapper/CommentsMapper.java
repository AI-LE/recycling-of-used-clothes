package com.mbyte.easy.recycle.mapper;

import com.mbyte.easy.recycle.entity.Comments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mbyte.easy.vo.commentsWithUser;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 评论表 Mapper 接口
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
@Component
public interface CommentsMapper extends BaseMapper<Comments> {
    List<commentsWithUser> selectLeftJoinWeixinUser(Integer goodsId);

}
