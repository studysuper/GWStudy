package com.bootdo.exercise.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;



/**
 * 试题答案表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperAnswerDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //试题选项
            private String testPaperAnswer;
                                //试题题目id
            private String testPaperTopicId;
            }
