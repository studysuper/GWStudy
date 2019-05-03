package com.bootdo.exercise.service;

import com.bootdo.common.utils.Query;
import com.bootdo.exercise.domain.TestPaperTypeDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bootdo.base.service.BaseService;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 试卷表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
public interface TestPaperTypeService extends BaseService<TestPaperTypeDO, String> {
    /**
     * @return void
     * @Author ZQ
     * @Description //解析 excel内容
     * @Date 2019/4/13 14:36
     * @Param [file]
     **/
    void doExcel(MultipartFile file, String id) throws IOException;

    int saveExp(TestPaperTypeDO testPaperType);

    List<TestPaperTypeExpDO> listExp(Query query);

    int countExp(Query query);

    int batchRemoveExp(String[] ids);
    /**
     * @Author ZQ
     * @Description //交卷功能实现
     * @Date 2019/5/2 23:41
     * @Param [map]
     * @return java.lang.String
     **/
    String finishTestPaper(Map<String, Object> map);

    List<TestPaperTypeDO> queryRecord(Map<String, Object> map);

    List<TestPaperTypeExpDO> querRecordDetial(Map<String, Object> map);
}
