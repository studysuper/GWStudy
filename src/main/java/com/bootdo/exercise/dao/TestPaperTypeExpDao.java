package com.bootdo.exercise.dao;

import com.bootdo.base.dao.BaseDao;
import com.bootdo.common.utils.Query;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 试卷表
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
@Mapper
public interface TestPaperTypeExpDao {

    List<TestPaperTypeExpDO> listExp(Query query);

    int countExp(Query query);
}
