package com.bootdo.base.service.impl;

import com.bootdo.base.dao.BaseDao;
import com.bootdo.base.domain.BaseDO;
import com.bootdo.base.service.BaseService;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author ZQ
 * @Description //基础的Service功能实现，去掉对应的Dao，实现增删改查功能
 * @Date 2019/3/22 15:47
 * @Param
 * @return
 **/
public class BaseServiceImpl<T extends BaseDO<PK>, PK> implements BaseService<T, PK> {
    @Autowired
    protected BaseDao<T, PK> baseDao;

    public BaseServiceImpl() {
    }

    protected String generateUUID() {
        return UUIDGenerator.uuid();
    }

    public T get(PK id) {
        Assert.notNull(id, "待查询数据主键不可为空");
        return this.baseDao.get(id);
    }

    public List<T> list(Map<String, Object> map) {
        return this.baseDao.list(map);
    }

    public int count(Map<String, Object> map) {
        return this.baseDao.count(map);
    }

    public int save(T t) {
        Assert.notNull(t, "待保存数据不可为空");
        if (t.getId() == null || "".equals(t.getId())) {
            ParameterizedType pt = (ParameterizedType) t.getClass().getGenericSuperclass();
            Type[] cs = pt.getActualTypeArguments();
            Class<PK> pkClass = (Class) cs[0];
            if (String.class.isAssignableFrom(pkClass)) {
                t.setId((PK) this.generateUUID());
            }
        }
        if (t.getCreatedate() == null) {
            t.setCreatedate(new Date());
            t.setModifydate(t.getCreatedate());
        }
        if(null!=ShiroUtils.getUser()){
            t.setOperator(ShiroUtils.getUser().getUsername());
        }
        return this.baseDao.save(t);
    }

    public int update(T t) {
        Assert.notNull(t, "待修改数据不可为空");
        Assert.notNull(t.getId(), "待修改数据主键可不为空");
        t.setModifydate(new Date());
        if(null!=ShiroUtils.getUser()){
            t.setOperator(ShiroUtils.getUser().getUsername());
        }
        return this.baseDao.update(t);
    }

    public int remove(PK id) {
        Assert.notNull(id, "待删除数据主键不可为空");
        return this.baseDao.remove(id);
    }

    public int batchRemove(PK... ids) {
        Assert.noNullElements(ids, "待删除数据主键不可为空");
        return this.baseDao.batchRemove(ids);
    }

}
