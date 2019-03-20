package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.exercise.dao.TestPaperOptionDao;
import com.bootdo.exercise.domain.TestPaperOptionDO;
import com.bootdo.exercise.service.TestPaperOptionService;



@Service
public class TestPaperOptionServiceImpl implements TestPaperOptionService {
	@Autowired
	private TestPaperOptionDao testPaperOptionDao;
	
	@Override
	public TestPaperOptionDO get(String id){
		return testPaperOptionDao.get(id);
	}
	
	@Override
	public List<TestPaperOptionDO> list(Map<String, Object> map){
		return testPaperOptionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testPaperOptionDao.count(map);
	}
	
	@Override
	public int save(TestPaperOptionDO testPaperOption){
		return testPaperOptionDao.save(testPaperOption);
	}
	
	@Override
	public int update(TestPaperOptionDO testPaperOption){
		return testPaperOptionDao.update(testPaperOption);
	}
	
	@Override
	public int remove(String id){
		return testPaperOptionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return testPaperOptionDao.batchRemove(ids);
	}
	
}
