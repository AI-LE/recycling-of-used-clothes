package com.mbyte.easy.recycle.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品详情页面图片表
 * </p>
 *
 * @author Author
 * @since 2019-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class GoodsImg extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 对应商品id
     */
    private Long goodsId;
    /**
     * 商品名字
     */
    @TableField(exist = false)
    private String goodsName;

    /**
     * 商品图片url
     */
    private String pic;

    /**
     * 状态（1：已删除；2：正常）
     */
    private Integer isDel;


}
