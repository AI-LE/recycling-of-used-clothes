package com.mbyte.easy;

import com.mbyte.easy.recycle.entity.Rate;
import com.mbyte.easy.recycle.service.IRateService;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther: wangzhen
 * @date: 19-4-11 20:28
 * @description: 测试空工具类
 */

public class TestEmpty extends TestCase {

    @Test
    public void test(){
            BigDecimal amount = new BigDecimal(1111);
        BigDecimal transferRate = new BigDecimal(0.3);
        BigDecimal money = amount.multiply(transferRate).setScale(2);
        System.out.println(money);
    }



}
