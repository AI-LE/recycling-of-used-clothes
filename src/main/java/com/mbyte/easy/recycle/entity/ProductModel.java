package com.mbyte.easy.recycle.entity;

import com.mbyte.easy.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kevinlyz
 * @ClassName ProductModel
 * @Description 商品支付实体
 * @Date 2019-07-20 16:13
 **/
public class ProductModel extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单金额
     */
    private String amount;

    /**
     * 订单描述
     */
    private String desc;

    /**
     * 地址
     */
    private String address;

    /**
     * 姓名
     */
    private String name;

    /**
     * 收件电话
     */
    private String tel;

    /**
     * 预约时间
     */
    private Date appointment;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        this.appointment = appointment;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "orderId='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", appointment=" + appointment +
                '}';
    }
}
