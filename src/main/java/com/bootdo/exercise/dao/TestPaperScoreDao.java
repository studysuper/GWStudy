package com.bootdo.exercise.dao;

import com.bootdo.exercise.domain.TestPaperScoreDO;

import java.util.List;
import java.util.Map;
import com.bootdo.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author zq
 * @email 519996418
 * @date 2019-05-03 00:24:35
 */
@Mapper
public interface TestPaperScoreDao extends BaseDao<TestPaperScoreDO, String>{

}
