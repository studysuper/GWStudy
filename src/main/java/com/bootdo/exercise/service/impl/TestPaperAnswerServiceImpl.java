package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.exercise.dao.TestPaperAnswerDao;
import com.bootdo.exercise.domain.TestPaperAnswerDO;
import com.bootdo.exercise.service.TestPaperAnswerService;



@Service
public class TestPaperAnswerServiceImpl implements TestPaperAnswerService {
	@Autowired
	private TestPaperAnswerDao testPaperAnswerDao;
	
	@Override
	public TestPaperAnswerDO get(String id){
		return testPaperAnswerDao.get(id);
	}
	
	@Override
	public List<TestPaperAnswerDO> list(Map<String, Object> map){
		return testPaperAnswerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testPaperAnswerDao.count(map);
	}
	
	@Override
	public int save(TestPaperAnswerDO testPaperAnswer){
		return testPaperAnswerDao.save(testPaperAnswer);
	}
	
	@Override
	public int update(TestPaperAnswerDO testPaperAnswer){
		return testPaperAnswerDao.update(testPaperAnswer);
	}
	
	@Override
	public int remove(String id){
		return testPaperAnswerDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return testPaperAnswerDao.batchRemove(ids);
	}
	
}
