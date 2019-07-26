package com.mbyte.easy.vo;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class commentsWithUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 对应的商品id
     */
    private Long goodsId;

    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 评论人的id
     */
    private Long userId;

    /**
     * 评论
     */
    private String comment;

    /**
     * 评论时间
     */
    private LocalDateTime createtime;


    /**
     * 评论时间
     */
    private Integer score;

    /**
     * 昵称
     */
    private String nickName="匿名用户";

    /**
     * 图片URL
     */
    private String imgUrl;

    /**
     * 头像图片
     */
    private String headPic="https://wx.qlogo.cn/mmopen/vi_32/jWUEA4p8Dnicic2eVX9pGm81nkAJYauiaso1bkG8pxKUg6KP6xpqSyLLkrBLibZ1AY8fhz5ZeUqjH2SxtJNE0cfHjQ/132";
}
