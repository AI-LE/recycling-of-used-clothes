
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
 * 商品订单
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ShopOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单状态（1：待付款 2：代发货 3：待收货 4：待评价）
     */
    private Integer status;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 买家id
     */
    private Long userId;

    /**
     * 下单时间
     */
    private LocalDateTime createtime;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 用户地址id
     */
    private Long addressId;
    /**
     * 用户地址
     */
    @TableField(exist = false)
    private String address;
    /**
     * 用户电话号码
     */
    private String phone;

    /**
     * 快递单号
     */
    private String express;

    /**
     * 订单是否展示（1：不展示；2：展示）
     */
    private Integer isShow;

    /**
     * 订单更新时间
     */
    private LocalDateTime updatetime;

    /**
     * 使否删除（1：删除；2：正常）
     */
    private Integer isDel;


}

