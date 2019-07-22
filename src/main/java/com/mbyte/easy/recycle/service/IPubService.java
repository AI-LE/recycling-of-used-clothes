package com.mbyte.easy.recycle.service;

import com.mbyte.easy.recycle.entity.ProductModel;

/**
 * <p>
 * 微信用户表 服务类
 * </p>
 *
 * @author Author
 * @since 2019-07-18
 */
public interface IPubService  {

    int wxPay(ProductModel product);
}
