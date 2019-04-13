package com.bootdo.exercise.dao;

import com.bootdo.exercise.domain.TestPaperTopicDO;

import java.util.List;
import java.util.Map;
import com.bootdo.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 试题题目表
 * @author zq
 * @email 519996418
 * @date 2019-04-13 15:30:25
 */
@Mapper
public interface TestPaperTopicDao extends BaseDao<TestPaperTopicDO, String>{

}
