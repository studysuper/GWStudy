package com.bootdo.exercise.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;



/**
 * 
 *
 * @author zq
 * @email 519996418
 * @date 2019-05-03 18:15:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperScoreDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //题目id
            private String topicId;
                                //对应的题目得分
            private Integer score;
                                //对应的这道题是否正确
            private Integer isture;
                                //对应的试卷的id
            private String testTypeId;
                                //每次做的题的id
            private String batchId;
                                //做题人答案
            private String yourAnswer;
            }
