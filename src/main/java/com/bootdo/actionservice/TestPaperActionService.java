package com.bootdo.actionservice;

import com.bootdo.common.utils.R;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @Author ZQ
 * @Description //题库相关接口
 * @Date 2019/4/23 14:37
 * @Param
 * @return
 **/
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
     * @return java.util.List<com.bootdo.exercise.domain.TestPaperTypeExpDO>
     * @Author ZQ
     * @Description //TODO
     * @Date 2019/4/20 19:33
     * @Param [map]
     **/
    @PostMapping("/queryTestByCode")
    List<TestPaperTypeExpDO> queryTestByCode(Map<String, Object> map);

    /**
     * @return void
     * @Author ZQ
     * @Description //下载文件
     * @Date 2019/4/23 19:06
     * @Param [map]
     **/
    @PostMapping("/createTestPaper")
    String createTestPaper(Map<String, Object> map, HttpServletRequest request,
                           HttpServletResponse response) throws IOException;

    /**
     * @return java.lang.String
     * @Author ZQ
     * @Description //交卷功能实现
     * @Date 2019/5/2 23:40
     * @Param [map]
     **/
    //finishTestPaper
    @PostMapping("/finishTestPaper")
    String finishTestPaper(Map<String, Object> map);

    /**
     * @return java.util.List<com.bootdo.exercise.domain.TestPaperTypeDO>
     * @Author ZQ
     * @Description //查询个人的答题记录
     * @Date 2019/5/3 17:32
     * @Param [map]
     **/
    @PostMapping("/queryRecord")
    List<TestPaperTypeDO> queryRecord(Map<String, Object> map);

    /**
     * @return java.util.List<com.bootdo.exercise.domain.TestPaperTypeDO>
     * @Author ZQ
     * @Description // 通过时间id查询id
     * @Date 2019/5/3 18:05
     * @Param [map]
     **/
    @PostMapping("/querRecordDetial")
    List<TestPaperTypeExpDO> querRecordDetial(Map<String, Object> map);
}
