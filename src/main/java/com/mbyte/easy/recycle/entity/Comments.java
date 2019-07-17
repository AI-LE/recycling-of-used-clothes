package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author Author
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Comments extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论
     */
    private String comment;

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


}
