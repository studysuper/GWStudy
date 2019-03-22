package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.exercise.dao.TestPaperOptionDao;
import com.bootdo.exercise.domain.TestPaperOptionDO;
import com.bootdo.exercise.service.TestPaperOptionService;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class TestPaperOptionServiceImpl extends BaseServiceImpl<TestPaperOptionDO, String> implements TestPaperOptionService {

}
