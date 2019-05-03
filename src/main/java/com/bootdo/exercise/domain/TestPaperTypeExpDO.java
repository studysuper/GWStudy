package com.bootdo.exercise.domain;

import com.bootdo.base.domain.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 试卷表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-26 20:02:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperTypeExpDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String topicId;
    //试卷编号
    private String testPaperNumber;
    //试卷题目
    private String exerciseTitle;
    //试题类型(01选择题（单选），02填空题，03判断题，04主观题)
    private String exerciseType;
    //试题时间（以分钟为单位）
    private Integer exerciseTime;
    //试卷总分
    private Integer exerciseNumber;
    //题目号
    private Integer testPaperNo;
    //试题题目
    private String testPaperTopic;
    //分数
    private Integer paperNumber;
    //试题选项
    private String testPaperOptionA;
    //试题选项
    private String testPaperOptionB;
    //试题选项
    private String testPaperOptionC;
    //试题选项
    private String testPaperOptionD;
    //试题答案
    private String testPaperAnswer;
    //你提交的答案
    private String yourAnswer;
}
