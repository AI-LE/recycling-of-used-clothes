package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 旧衣类型表
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ClothesType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 旧衣类型
     */
    private String type;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 状态（1：已删除；2：正常）
     */
    private Integer isDel;


}
