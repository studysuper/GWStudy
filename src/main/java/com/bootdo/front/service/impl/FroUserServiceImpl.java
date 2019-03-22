package com.bootdo.front.service.impl;

import com.bootdo.common.utils.ConstantUtil;
import com.bootdo.sys.service.MaxNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.front.domain.FroUserDO;
import com.bootdo.front.service.FroUserService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FroUserServiceImpl extends BaseServiceImpl<FroUserDO, String> implements FroUserService {

    @Autowired
    private MaxNoService maxNoService;

    @Override
    public int saveExp(FroUserDO froUser) {
        froUser.setUserNo(ConstantUtil.USER_NO + "0000" + maxNoService.getMaxNo(ConstantUtil.USER_NO));
        return this.save(froUser);
    }
}
