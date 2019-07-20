package com.mbyte.easy.recycle.entity;

import java.math.BigDecimal;
import com.mbyte.easy.common.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信用户表
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WeixinUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 性别 1：男
     */
    private Integer gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 头像地址#img
     */
    @TableField("avatarUrl")
    private String avatarUrl;

    /**
     * 唯一主键id
     */
    @TableField("openId")
    private String openId;

    /**
     * 用户类型表（1：普通用户；2：取货员）
     */
    private Integer type;

    /**
     * 账户余额
     */
    private BigDecimal account;

    /**
     * 电话号码
     */
    private String phone;


}
