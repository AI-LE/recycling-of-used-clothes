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
 * 
 * </p>
 *
 * @author Author
 * @since 2019-07-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TransferDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 提现单号
     */
    private String transferNo;

    /**
     * 提现金额
     */
    private BigDecimal price;

    private LocalDateTime createtime;

    private  Integer status;


    @TableField(exist = false)
    private String name;


}
