package com.bootdo.actionservice;

import com.bootdo.common.utils.R;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@RequestMapping("/api/testpaper")
public interface TestPaperActionService {
    /**
     * @return java.util.List<com.bootdo.exercise.domain.TestPaperTypeDO>
     * @Author ZQ
     * @Description //TODO
     * @Date 2019/4/20 17:48
     * @Param [map] 对应的（model（考前练习等参数））
     **/
    @PostMapping("/queryTestPaperAll")
    List<TestPaperTypeDO> queryTestPaperAll(Map<String, Object> map);

    /**
     * @Author ZQ
     * @Description //TODO 
     * @Date 2019/4/20 19:33
     * @Param [map]
     * @return java.util.List<com.bootdo.exercise.domain.TestPaperTypeExpDO>
     **/
    @PostMapping("/queryTestByCode")
    List<TestPaperTypeExpDO> queryTestByCode(Map<String, Object> map);
}
