package com.bootdo.exercise.dao;

import com.bootdo.exercise.domain.TestPaperAnswerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 试题答案表
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:05
 */
@Mapper
public interface TestPaperAnswerDao {

	TestPaperAnswerDO get(String id);
	
	List<TestPaperAnswerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestPaperAnswerDO testPaperAnswer);
	
	int update(TestPaperAnswerDO testPaperAnswer);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
