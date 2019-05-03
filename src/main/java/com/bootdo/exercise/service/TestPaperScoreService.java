package com.bootdo.exercise.service;

import com.bootdo.exercise.domain.TestPaperScoreDO;

import java.util.List;
import java.util.Map;
import com.bootdo.base.service.BaseService;
/**
 * 
 * 
 * @author zq
 * @email 519996418
 * @date 2019-05-03 00:24:35
 */
public interface TestPaperScoreService extends BaseService<TestPaperScoreDO,String>{

    int getSumScore(String testTypeId);
}
