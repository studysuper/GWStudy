package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.exercise.dao.TestPaperTypeDao;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.service.TestPaperTypeService;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class TestPaperTypeServiceImpl extends BaseServiceImpl<TestPaperTypeDO, String> implements TestPaperTypeService {

}
