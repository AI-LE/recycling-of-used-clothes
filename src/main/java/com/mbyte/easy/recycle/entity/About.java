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
 * @since 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class About extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关于我们
     */
    private String content;


}
