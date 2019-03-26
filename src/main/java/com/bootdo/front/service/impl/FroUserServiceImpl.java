package com.bootdo.front.service.impl;

import com.bootdo.common.utils.ConstantUtil;
import com.bootdo.front.dao.FroUserExpDao;
import com.bootdo.sys.service.MaxNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.front.domain.FroUserDO;
import com.bootdo.front.service.FroUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Service
@Transactional
public class FroUserServiceImpl extends BaseServiceImpl<FroUserDO, String> implements FroUserService {

    @Autowired
    private MaxNoService maxNoService;
    @Autowired //注入dao
    private FroUserExpDao froUserExpDao;
    @Override
    public int saveExp(FroUserDO froUser) {
        froUser.setUserNo(ConstantUtil.USER_NO + "0000" + maxNoService.getMaxNo(ConstantUtil.USER_NO));
        return this.save(froUser);
    }

    @Override
    public int loginExp(FroUserDO froUser) {
        Map<String,Object> map = new HashMap<>();
        map.put("userName",froUser.getUserName());
        map.put("password",froUser.getPassword());
        return froUserExpDao.getByNameAndPwd(map);
    }

    @Override
    public int getUserName(FroUserDO froUser) {
        Map<String,Object> map = new HashMap<>();
        map.put("userName",froUser.getUserName());
        return froUserExpDao.getByNameAndPwd(map);
    }


}
