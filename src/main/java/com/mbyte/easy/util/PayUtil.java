package com.mbyte.easy.util;

/**
 * @author kevinlyz
 * @ClassName PayUtil
 * @Description 支付所需工具类
 * @Date 2019-07-21 13:39
 **/
public class PayUtil {

    /**
     * 获取唯一订单号
     * @param userId
     * @return
     */
    public static String getUniqueOrderId(Integer userId){
        return String.valueOf(System.currentTimeMillis()+(userId+Math.random()*100));
    }
}
