package com.mbyte.easy.recycle.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author Author
 * @since 2019-07-19
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
     * 销量
     */
    private Integer sales;

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
     * 商品类型
     */
    @TableField(exist = false)
    private String goodsType;

    /**
     * 货物创建时间
     */
    private LocalDateTime createtime;

    /**
     * 信息更新时间
     */
    private LocalDateTime updatetime;

    /**
     * 状态（1：已删除；2：正常）
     */
    private Integer isDel=2;




    /**
     * 商品数量
     */
    @TableField(exist = false)
    private String buyNum;


}
