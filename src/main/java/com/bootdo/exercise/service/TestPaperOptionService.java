package com.bootdo.exercise.service;

import com.bootdo.exercise.domain.TestPaperOptionDO;

import java.util.List;
import java.util.Map;

/**
 * 试题选项表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
public interface TestPaperOptionService {
	
	TestPaperOptionDO get(String id);
	
	List<TestPaperOptionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestPaperOptionDO testPaperOption);
	
	int update(TestPaperOptionDO testPaperOption);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
