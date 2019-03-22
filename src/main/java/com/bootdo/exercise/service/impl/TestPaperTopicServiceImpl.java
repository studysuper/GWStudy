package com.bootdo.exercise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.exercise.dao.TestPaperTopicDao;
import com.bootdo.exercise.domain.TestPaperTopicDO;
import com.bootdo.exercise.service.TestPaperTopicService;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class TestPaperTopicServiceImpl extends BaseServiceImpl<TestPaperTopicDO, String> implements TestPaperTopicService {

}
