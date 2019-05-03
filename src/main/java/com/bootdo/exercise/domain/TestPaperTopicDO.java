package com.bootdo.exercise.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;



/**
 * 试题题目表
 *
 * @author zq
 * @email 519996418
 * @date 2019-05-02 15:40:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperTopicDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //题目号
            private Integer testPaperNo;
                                //试题题目
            private String testPaperTopic;
                                //试题类型id
            private String testPaperTypeId;
                                //分数
            private Integer testPaperNumber;
                                //内容类型(常识判断，言语理解与表达，数量关系，判断推理，资料分析)
            private Integer testPaperType;
            }
