package com.bootdo.base.service;

import com.bootdo.base.domain.BaseDO;
import com.bootdo.front.domain.FroUserDO;

import java.util.List;
import java.util.Map;

/**
 * @Author ZQ
 * @Description //基础的service接口
 * @Date 2019/3/22 14:17
 * @Param
 * @return
 **/
public interface BaseService<T extends BaseDO<PK>, PK> {

    /**
     * @return T
     * @Author ZQ
     * @Description //通过id查询
     * @Date 2019/3/22 16:02
     * @Param [var1]
     **/
    T get(PK var1);

    /**
     * @return java.util.List<T>
     * @Author ZQ
     * @Description //通过条件查询list
     * @Date 2019/3/22 16:02
     * @Param [var1]
     **/
    List<T> list(Map<String, Object> var1);

    /**
     * @return int
     * @Author ZQ
     * @Description //统计条数
     * @Date 2019/3/22 16:02
     * @Param [var1]
     **/
    int count(Map<String, Object> var1);

    /**
     * @return int
     * @Author ZQ
     * @Description //插入一条数据
     * @Date 2019/3/22 16:03
     * @Param [var1]
     **/
    int save(T var1);

    /**
     * @return int
     * @Author ZQ
     * @Description //更新一条记录
     * @Date 2019/3/22 16:03
     * @Param [var1]
     **/
    int update(T var1);

    /**
     * @return int
     * @Author ZQ
     * @Description //通过id删除一条记录
     * @Date 2019/3/22 16:03
     * @Param [var1]
     **/
    int remove(PK var1);

    /**
     * @return int
     * @Author ZQ
     * @Description //通过id进行批量删除
     * @Date 2019/3/22 16:03
     * @Param [var1]
     **/
    int batchRemove(PK... var1);
}
