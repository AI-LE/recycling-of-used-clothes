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
 * 回收订单表
 * </p>
 *
 * @author 艾乐
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RecycleOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Long userId;

    private Long courierId;
    /**
     * 取货员名字
     */
    @TableField(exist = false)
    private String nickName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 预约取货时间 
     */
    private LocalDateTime appointment;

    /**
     * 取货地址
     */
    private Long addressId;
    /**
     * 取货地址
     */
    @TableField(exist = false)
    private String address;

    private String phone;
 
    /**
     * 取货码
     */
    private String pickCode;

    /**
     * 订单状态（1：待审核；2：未通过；3：待取货；4：交易完成）
     */
    private Integer status;

    /**
     * 旧衣图片地址
     */
    private String imageUrl;

    /**
     * 订单创建时间
     */
    private LocalDateTime createtime;

    /**
     * 订单更新时间
     */
    private LocalDateTime updatetime;

    /**
     * 是否删除（1：）
     */
    private Integer isShow;

    /**
     * 状态（1：已删除；2：正常）
     */
    private Integer isDel;


}
