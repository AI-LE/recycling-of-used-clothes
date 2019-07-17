package com.mbyte.easy.recycle.entity;

import java.math.BigDecimal;
import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Goods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 物品详情
     */
    private String info;

    /**
     * 商品类型id
     */
    private Long goodsTypeId;

    /**
     * 状态（1：已删除；2：正常）
     */
    private Integer isDel;


}
