package com.bootdo.front.dao;

import com.bootdo.front.domain.FroUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 前端用户表
 * @author zq
 * @email 519996418
 * @date 2019-03-20 14:44:59
 */
@Mapper
public interface FroUserDao {

	FroUserDO get(String id);
	
	List<FroUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FroUserDO froUser);
	
	int update(FroUserDO froUser);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
