package com.bootdo.front.service;

import com.bootdo.front.domain.FroUserDO;

import java.util.List;
import java.util.Map;

import com.bootdo.base.service.BaseService;

/**
 * 前端用户表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:04:13
 */
public interface FroUserService extends BaseService<FroUserDO, String> {
    /**
     * @return int
     * @Author ZQ
     * @Description //重新新增方法
     * @Date 2019/3/22 18:16
     * @Param [froUser]
     **/
    int saveExp(FroUserDO froUser);

    int loginExp(FroUserDO froUser);

    int getUserName(FroUserDO froUser);
}
