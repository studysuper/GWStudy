package com.bootdo.exercise.dao;

import com.bootdo.exercise.domain.TestPaperOptionDO;

import java.util.List;
import java.util.Map;
import com.bootdo.base.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 试题选项表
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
@Mapper
public interface TestPaperOptionDao extends BaseDao<TestPaperOptionDO, String>{

}
