package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.exercise.dao.TestPaperTypeDao;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.service.TestPaperTypeService;



@Service
public class TestPaperTypeServiceImpl implements TestPaperTypeService {
	@Autowired
	private TestPaperTypeDao testPaperTypeDao;
	
	@Override
	public TestPaperTypeDO get(String id){
		return testPaperTypeDao.get(id);
	}
	
	@Override
	public List<TestPaperTypeDO> list(Map<String, Object> map){
		return testPaperTypeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testPaperTypeDao.count(map);
	}
	
	@Override
	public int save(TestPaperTypeDO testPaperType){
		return testPaperTypeDao.save(testPaperType);
	}
	
	@Override
	public int update(TestPaperTypeDO testPaperType){
		return testPaperTypeDao.update(testPaperType);
	}
	
	@Override
	public int remove(String id){
		return testPaperTypeDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return testPaperTypeDao.batchRemove(ids);
	}
	
}
