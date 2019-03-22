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
 * @date 2019-03-22 18:11:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperTypeDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //试卷题目
            private String exerciseTitle;
                                //试题类型(01选择题（单选），02填空题，03判断题，04主观题)
            private String exerciseType;
            }
