package com.bootdo.base.dao;

import com.bootdo.base.domain.BaseDO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseDao
 * @Description 基础的Dao，提供基础的增删改查
 * @Author ZQ
 * @Date 2019/3/22 15:33
 */
public interface BaseDao<T extends BaseDO<PK>, PK> {
    T get(PK var1);

    List<T> list(Map<String, Object> var1);

    int count(Map<String, Object> var1);

    int save(T var1);

    int update(T var1);

    int remove(PK var1);

    int batchRemove(PK... var1);
}
