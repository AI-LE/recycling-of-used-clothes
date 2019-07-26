package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Comments extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 对应的商品id
     */
    private Long goodsId;

    /**
     * 父评论id
     */
    private Long parentId=new Long(999);

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

}
