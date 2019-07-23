package com.mbyte.easy.vo;

import lombok.Data;

@Data
public class WxReqVo {

    private String  nonceStr;
    private String  outTradeNo;
    private String  signType;
    private String  paySign;

}
