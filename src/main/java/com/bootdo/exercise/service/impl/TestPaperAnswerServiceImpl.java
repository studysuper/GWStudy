package com.bootdo.exercise.service.impl;

import com.bootdo.exercise.dao.TestPaperAnswerExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.exercise.dao.TestPaperAnswerDao;
import com.bootdo.exercise.domain.TestPaperAnswerDO;
import com.bootdo.exercise.service.TestPaperAnswerService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TestPaperAnswerServiceImpl extends BaseServiceImpl<TestPaperAnswerDO, String> implements TestPaperAnswerService {

    private final TestPaperAnswerExpDao answerExpDao;

    @Autowired
    public TestPaperAnswerServiceImpl(TestPaperAnswerExpDao answerExpDao) {
        this.answerExpDao = answerExpDao;
    }

    @Override
    public TestPaperAnswerDO queryByTopicId(String topicId) {
        return answerExpDao.queryByTopicId(topicId);
    }
}
