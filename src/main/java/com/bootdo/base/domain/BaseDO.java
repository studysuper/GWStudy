package com.bootdo.base.domain;

import lombok.Data;

import java.util.Date;

/**
 * @Author ZQ
 * @Description 所有DO的基础类
 * @Date 2019/3/22 14:17
 * @Param
 * @return
 **/
@Data
public class BaseDO<T> {
    private static final long serialVersionUID = 1253855922292878867L;
    private T id;
    private String operator;
    private Date createdate;
    private Date modifydate;
}
