package com.bootdo.sys.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;



/**
 * 系统最大号表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 19:12:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MaxNoDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //关键key
            private String codeKey;
                                //关键value
            private Integer codeValue;
            }
