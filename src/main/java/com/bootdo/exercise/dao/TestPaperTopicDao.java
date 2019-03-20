package com.bootdo.exercise.dao;

import com.bootdo.exercise.domain.TestPaperTopicDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 试题题目表
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
@Mapper
public interface TestPaperTopicDao {

	TestPaperTopicDO get(String id);
	
	List<TestPaperTopicDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestPaperTopicDO testPaperTopic);
	
	int update(TestPaperTopicDO testPaperTopic);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
