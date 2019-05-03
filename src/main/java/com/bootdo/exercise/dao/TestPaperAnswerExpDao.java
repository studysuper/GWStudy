package com.bootdo.exercise.dao;

import com.bootdo.base.dao.BaseDao;
import com.bootdo.exercise.domain.TestPaperAnswerDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 试题答案表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
@Mapper
public interface TestPaperAnswerExpDao {

    TestPaperAnswerDO queryByTopicId(String topicId);
}
