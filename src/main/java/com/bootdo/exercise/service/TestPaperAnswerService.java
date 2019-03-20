package com.bootdo.exercise.service;

import com.bootdo.exercise.domain.TestPaperAnswerDO;

import java.util.List;
import java.util.Map;

/**
 * 试题答案表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:05
 */
public interface TestPaperAnswerService {
	
	TestPaperAnswerDO get(String id);
	
	List<TestPaperAnswerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestPaperAnswerDO testPaperAnswer);
	
	int update(TestPaperAnswerDO testPaperAnswer);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
