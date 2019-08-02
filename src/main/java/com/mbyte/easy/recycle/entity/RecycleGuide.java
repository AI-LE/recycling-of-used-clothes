package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Author
 * @since 2019-08-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RecycleGuide extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String cont;

    /**
     * 内容
     */
    private String con;

    /**
     * 是否隐藏
     */
    private Boolean hiddena;

    /**
     * 下拉标（图片地址）
     */
    private String down;


}
