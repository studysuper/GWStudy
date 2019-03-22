package com.bootdo.sys.dao;

import com.bootdo.base.dao.BaseDao;
import com.bootdo.sys.domain.MaxNoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统最大号表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 19:12:58
 */
@Mapper
public interface MaxNoExpDao {

    MaxNoDO getByKCode(String k);
}
