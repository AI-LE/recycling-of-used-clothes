package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户属性表
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserProp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 对应微信用户id
     */
    private Long userId;

    /**
     * 收货电话
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 状态（1：已删除；2：正常）
     */
    private Integer isDel;

    /**
     * 用户姓名
     */
    private String userName;


}
