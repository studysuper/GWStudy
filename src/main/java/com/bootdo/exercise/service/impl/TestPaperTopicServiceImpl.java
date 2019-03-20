package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.exercise.dao.TestPaperTopicDao;
import com.bootdo.exercise.domain.TestPaperTopicDO;
import com.bootdo.exercise.service.TestPaperTopicService;



@Service
public class TestPaperTopicServiceImpl implements TestPaperTopicService {
	@Autowired
	private TestPaperTopicDao testPaperTopicDao;
	
	@Override
	public TestPaperTopicDO get(String id){
		return testPaperTopicDao.get(id);
	}
	
	@Override
	public List<TestPaperTopicDO> list(Map<String, Object> map){
		return testPaperTopicDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testPaperTopicDao.count(map);
	}
	
	@Override
	public int save(TestPaperTopicDO testPaperTopic){
		return testPaperTopicDao.save(testPaperTopic);
	}
	
	@Override
	public int update(TestPaperTopicDO testPaperTopic){
		return testPaperTopicDao.update(testPaperTopic);
	}
	
	@Override
	public int remove(String id){
		return testPaperTopicDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return testPaperTopicDao.batchRemove(ids);
	}
	
}
