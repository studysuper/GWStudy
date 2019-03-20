package com.bootdo.exercise.dao;

import com.bootdo.exercise.domain.TestPaperTypeDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 试卷表
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
@Mapper
public interface TestPaperTypeDao {

	TestPaperTypeDO get(String id);
	
	List<TestPaperTypeDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestPaperTypeDO testPaperType);
	
	int update(TestPaperTypeDO testPaperType);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
