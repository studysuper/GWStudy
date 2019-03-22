package com.bootdo.sys.service.impl;

import com.bootdo.common.utils.ConstantUtil;
import com.bootdo.common.utils.UUIDGenerator;
import com.bootdo.sys.dao.MaxNoExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.sys.domain.MaxNoDO;
import com.bootdo.sys.service.MaxNoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;


@Service
@Transactional
public class MaxNoServiceImpl extends BaseServiceImpl<MaxNoDO, String> implements MaxNoService {

    @Autowired
    private MaxNoExpDao maxNoExpDao;

    @Override
    public MaxNoDO getByKCode(String k) {
        return this.maxNoExpDao.getByKCode(k);
    }

    @Override
    public int getMaxNo(String key) {
        int maxNo = 0;
        if (!StringUtils.isEmpty(key)) {
            MaxNoDO maxNoDO = this.getByKCode(key);
            if (null == maxNoDO) {
                //进行初始化一个值
                maxNoDO = new MaxNoDO();
                maxNoDO.setId(UUIDGenerator.uuid());
                maxNoDO.setCreatedate(new Date());
                maxNoDO.setModifydate(new Date());
                maxNoDO.setCodeKey(key);
                maxNoDO.setCodeValue(ConstantUtil.MIN_VALUE);
                this.save(maxNoDO);
                maxNo = ConstantUtil.MIN_VALUE;
            } else {
                maxNo = maxNoDO.getCodeValue() + 1;
                maxNoDO.setCodeValue(maxNo);
                this.update(maxNoDO);
            }
        }
        return maxNo;
    }
}
