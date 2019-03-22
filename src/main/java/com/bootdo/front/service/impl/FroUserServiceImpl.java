package com.bootdo.front.service.impl;

import com.bootdo.util.SysMaxNo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.front.dao.FroUserDao;
import com.bootdo.front.domain.FroUserDO;
import com.bootdo.front.service.FroUserService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FroUserServiceImpl extends BaseServiceImpl<FroUserDO, String> implements FroUserService {

    @Override
    public int saveExp(FroUserDO froUser) {
        froUser.setUserNo(SysMaxNo.getInstance().maxNo("").getMaxNo());
        return this.save(froUser);
    }
}
