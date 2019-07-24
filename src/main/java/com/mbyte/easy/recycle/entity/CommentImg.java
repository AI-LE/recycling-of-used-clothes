package com.mbyte.easy.recycle.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mbyte.easy.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Author
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("commentImg")
public class CommentImg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    private Long commentid;

    /**
     * 评论图片url
     */
    @TableField("picUrl")
    private String picUrl;


}
