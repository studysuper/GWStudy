package com.bootdo.front.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.front.dao.FroUserDao;
import com.bootdo.front.domain.FroUserDO;
import com.bootdo.front.service.FroUserService;



@Service
public class FroUserServiceImpl implements FroUserService {
	@Autowired
	private FroUserDao froUserDao;
	
	@Override
	public FroUserDO get(String id){
		return froUserDao.get(id);
	}
	
	@Override
	public List<FroUserDO> list(Map<String, Object> map){
		return froUserDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return froUserDao.count(map);
	}
	
	@Override
	public int save(FroUserDO froUser){
		return froUserDao.save(froUser);
	}
	
	@Override
	public int update(FroUserDO froUser){
		return froUserDao.update(froUser);
	}
	
	@Override
	public int remove(String id){
		return froUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return froUserDao.batchRemove(ids);
	}
	
}
