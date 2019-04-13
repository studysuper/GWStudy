package com.bootdo.common.service;

import com.bootdo.common.domain.DictDO;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface DictService {

    DictDO get(Long id);

    List<DictDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(DictDO dict);

    int update(DictDO dict);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<DictDO> listType();

    String getName(String type, String value);

    /**
     * 获取爱好列表
     *
     * @param userDO
     * @return
     */
    List<DictDO> getHobbyList(UserDO userDO);

    /**
     * 获取性别列表
     *
     * @return
     */
    List<DictDO> getSexList();

    /**
     * 根据type获取数据
     *
     * @return
     */
    List<DictDO> listByType(String type);

    /**
     * @return java.util.List<com.bootdo.common.domain.DictDO>
     * @Author ZQ
     * @Description //通过type 查询字典表
     * @Date 2019/3/25 23:28
     * @Param [dictCode]
     **/
    List<DictDO> childDictList(String dictCode);
}
