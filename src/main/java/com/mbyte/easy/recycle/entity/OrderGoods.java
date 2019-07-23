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
 * @since 2019-07-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderGoods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 货物id
     */
    private Long goodsid;


}
