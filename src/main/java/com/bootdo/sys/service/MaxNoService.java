package com.bootdo.sys.service;

import com.bootdo.sys.domain.MaxNoDO;

import java.util.List;
import java.util.Map;

import com.bootdo.base.service.BaseService;

/**
 * 系统最大号表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 19:12:58
 */
public interface MaxNoService extends BaseService<MaxNoDO, String> {
    /**
     * @return com.bootdo.sys.domain.MaxNoDO
     * @Author ZQ
     * @Description //通过编码查询信息
     * @Date 2019/3/22 19:30
     * @Param [k]
     **/
    MaxNoDO getByKCode(String k);

    /**
     * @return int
     * @Author ZQ
     * @Description //得到最大号
     * @Date 2019/3/22 20:18
     * @Param [key]
     **/
    int getMaxNo(String key);
}
