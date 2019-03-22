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
 * @date 2019-03-22 18:11:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TestPaperTopicDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = 1L;

                                                                        //试题题目
            private String testPaperTopic;
                                //试题类型id
            private String testPaperTypeId;
            }
