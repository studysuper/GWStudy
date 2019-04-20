package com.bootdo.exercise.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;



/**
 * 试卷表
 *
 * @author zq
 * @email 519996418
 * @date 2019-04-20 17:03:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperTypeDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //试卷编号
            private String testPaperNumber;
                                //试卷题目
            private String exerciseTitle;
                                //题库类型(行测测试，申论测试)
            private String exerciseType;
                                //试题时间（以分钟为单位）
            private Integer exerciseTime;
                                //试卷总分
            private Integer exerciseNumber;
                                //试卷模块（考前冲刺，专项训练，历年真题，错题重练）
            private Integer exerciseMode;
            }
