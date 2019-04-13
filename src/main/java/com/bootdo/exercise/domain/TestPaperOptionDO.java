package com.bootdo.exercise.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;



/**
 * 试题选项表
 *
 * @author zq
 * @email 519996418
 * @date 2019-04-13 15:47:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperOptionDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //试题题目id
            private String testPaperTopicId;
                                //试题选项
            private String testPaperOptionA;
                                //试题选项
            private String testPaperOptionB;
                                //试题选项
            private String testPaperOptionC;
                                //试题选项
            private String testPaperOptionD;
            }
