package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author 魏皓
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ShopCar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态（1:已删除；2：正常）
     */
    private Integer isDel;


}
