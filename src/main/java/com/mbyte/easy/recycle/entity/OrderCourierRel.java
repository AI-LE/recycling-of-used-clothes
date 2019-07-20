package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 取货员订单联系表
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderCourierRel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 取货员id
     */
    private Long courierId;

    /**
     * 取货码
     */
    private String pickCode;


}
