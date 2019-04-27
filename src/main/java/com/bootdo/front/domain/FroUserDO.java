package com.bootdo.front.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;



/**
 * 前端用户表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:04:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FroUserDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //前端用户编码(系统生成)
            private String userNo;
                                //前端用户姓名
            private String userName;
                                //密码
            private String password;
                                //年龄
            private Integer age;
                                //手机号
            private Integer phone;
                                //邮箱
            private String email;
                                //身份证号
            private String idno;
                                //头像
            private String headPortrait;
                                //家庭住址
            private String address;
            }
