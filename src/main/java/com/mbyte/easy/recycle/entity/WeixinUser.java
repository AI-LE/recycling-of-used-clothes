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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
