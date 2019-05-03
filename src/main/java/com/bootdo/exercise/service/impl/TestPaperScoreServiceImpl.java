package com.bootdo.exercise.service.impl;

import com.bootdo.exercise.dao.TestPaperScoreExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.base.service.impl.BaseServiceImpl;
import com.bootdo.exercise.dao.TestPaperScoreDao;
import com.bootdo.exercise.domain.TestPaperScoreDO;
import com.bootdo.exercise.service.TestPaperScoreService;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TestPaperScoreServiceImpl extends BaseServiceImpl<TestPaperScoreDO, String> implements TestPaperScoreService {

    private final TestPaperScoreExpDao scoreExpDao;

    @Autowired
    public TestPaperScoreServiceImpl(TestPaperScoreExpDao scoreExpDao) {
        this.scoreExpDao = scoreExpDao;
    }

    @Override
    public int getSumScore(String testTypeId) {
        return scoreExpDao.getSumScore(testTypeId);
    }
}
