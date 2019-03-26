package com.bootdo.front.dao;

import com.bootdo.base.dao.BaseDao;
import com.bootdo.front.domain.FroUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 前端用户表
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:04:13
 */
@Mapper
public interface FroUserExpDao {
    int getByNameAndPwd(Map<String,Object> param);
}
