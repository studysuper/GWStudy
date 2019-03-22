package com.bootdo.front.dao;

import com.bootdo.front.domain.FroUserDO;

import java.util.List;
import java.util.Map;
import com.bootdo.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 前端用户表
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:04:13
 */
@Mapper
public interface FroUserDao extends BaseDao<FroUserDO, String>{

}
