package com.mbyte.easy.recycle.entity;

import java.math.BigDecimal;
import com.mbyte.easy.common.entity.BaseEntity;
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
public class Rate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private BigDecimal withdrawalRate;

    private BigDecimal payRate;

    private BigDecimal kgRate;


}
