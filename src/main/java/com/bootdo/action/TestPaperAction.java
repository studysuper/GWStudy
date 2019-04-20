package com.bootdo.action;

import com.bootdo.actionservice.TestPaperActionService;
import com.bootdo.common.utils.Query;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import com.bootdo.exercise.service.TestPaperTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 接口的Action类，相当于本系统原来的Controller
 */
@RestController
public class TestPaperAction implements TestPaperActionService {

    private final TestPaperTypeService paperTypeService;

    @Autowired
    public TestPaperAction(TestPaperTypeService paperTypeService) {
        this.paperTypeService = paperTypeService;
    }

    @Override
    public List<TestPaperTypeDO> queryTestPaperAll(@RequestBody Map<String, Object> map) {
        return paperTypeService.list(map);
    }

    @Override
    public List<TestPaperTypeExpDO> queryTestByCode(@RequestBody Map<String, Object> map) {
        Query query = new Query(map);
        return paperTypeService.listExp(query);
    }
}
