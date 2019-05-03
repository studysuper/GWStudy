package com.bootdo.exercise.service.impl;

import com.bootdo.common.utils.ConstantUtil;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.common.utils.UUIDGenerator;
import com.bootdo.exercise.dao.TestPaperTypeDao;
import com.bootdo.exercise.dao.TestPaperTypeExpDao;
import com.bootdo.exercise.domain.*;
import com.bootdo.exercise.service.*;
import com.bootdo.sys.service.MaxNoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.*;

import com.bootdo.base.service.impl.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@Transactional
public class TestPaperTypeServiceImpl extends BaseServiceImpl<TestPaperTypeDO, String> implements TestPaperTypeService {

    private final TestPaperTopicService topicService;
    private final TestPaperOptionService optionService;
    private final TestPaperAnswerService answerService;
    private final MaxNoService maxNoService;
    private final TestPaperTypeExpDao typeExpDao;
    private final TestPaperScoreService scoreService;

    @Autowired
    public TestPaperTypeServiceImpl(TestPaperTopicService topicService,
                                    TestPaperOptionService optionService,
                                    TestPaperAnswerService answerService,
                                    MaxNoService maxNoService,
                                    TestPaperTypeExpDao typeExpDao,
                                    TestPaperScoreService scoreService) {
        this.topicService = topicService;
        this.optionService = optionService;
        this.answerService = answerService;
        this.maxNoService = maxNoService;
        this.typeExpDao = typeExpDao;
        this.scoreService = scoreService;
    }

    @Override
    public void doExcel(MultipartFile file, String id) throws IOException {
        TestPaperTopicDO topicDO = null;//试题主干
        TestPaperOptionDO optionDO = null;//试题选项
        TestPaperAnswerDO answerDO = null;//试题答案
        InputStream is = file.getInputStream();
        //获取工作薄中的所有sheets
        XSSFWorkbook sheets = new XSSFWorkbook(is);
        for (Sheet sheet : sheets) {
            //不读标题行
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(0);
                String content = getCellStringVal(cell).trim();
                if (content.equals("000000")) {//为了是去除空的内容，是以第一列来判断的
                    continue;
                }
                topicDO = new TestPaperTopicDO();
                optionDO = new TestPaperOptionDO();
                answerDO = new TestPaperAnswerDO();
                String topicId = UUIDGenerator.uuid();
                topicDO.setId(topicId);
                topicDO.setTestPaperTypeId(id);
                topicDO.setTestPaperNo(Integer.valueOf(getCellStringVal(row.getCell(1)).trim()));//题号
                topicDO.setTestPaperTopic(getCellStringVal(row.getCell(2)).trim());//题目
                topicDO.setTestPaperNumber(Integer.valueOf(getCellStringVal(row.getCell(3)).trim()));//分数
                optionDO.setTestPaperTopicId(topicId);
                optionDO.setTestPaperOptionA(getCellStringVal(row.getCell(4)).trim());
                optionDO.setTestPaperOptionB(getCellStringVal(row.getCell(5)).trim());
                optionDO.setTestPaperOptionC(getCellStringVal(row.getCell(6)).trim());
                optionDO.setTestPaperOptionD(getCellStringVal(row.getCell(7)).trim());
                topicDO.setTestPaperType(Integer.valueOf(getCellStringVal(row.getCell(9)).trim()));//内容类型

                answerDO.setTestPaperTopicId(topicId);
                answerDO.setTestPaperAnswer(getCellStringVal(row.getCell(8)).trim());
                this.topicService.save(topicDO);
                this.optionService.save(optionDO);
                this.answerService.save(answerDO);
            }
        }
        //关闭对应的流
        is.close();
        sheets.close();
    }


    @Override
    public int saveExp(TestPaperTypeDO testPaperType) {
        testPaperType.setTestPaperNumber(ConstantUtil.TEST_PAPERT_NO + "0000" + maxNoService.getMaxNo(ConstantUtil.TEST_PAPERT_NO));
        return this.save(testPaperType);
    }

    @Override
    public List<TestPaperTypeExpDO> listExp(Query query) {
        return this.typeExpDao.listExp(query);
    }

    @Override
    public int countExp(Query query) {
        return this.typeExpDao.countExp(query);
    }

    @Override
    public int batchRemoveExp(String[] ids) {
        Map<String, Object> map = new HashMap<>();
        for (String id : ids) {
            map.put("testPaperTopicId", id);
            List<TestPaperOptionDO> optionList = this.optionService.list(map);
            List<TestPaperAnswerDO> answerList = this.answerService.list(map);
            for (TestPaperOptionDO optionDO : optionList) {
                this.optionService.remove(optionDO.getId());
            }
            for (TestPaperAnswerDO answer : answerList) {
                this.answerService.remove(answer.getId());
            }
            this.topicService.remove(id);
        }
        return 1;
    }

    /**
     * @return java.lang.String
     * @Author ZQ
     * @Description //处理交卷业务功能实现
     * @Date 2019/5/2 23:42
     * @Param [map]
     **/
    @Override
    public String finishTestPaper(Map<String, Object> map) {
        List list = (List) map.get("anwerMap");
        String operator = "" + map.get("operator");
        String testTypeId = "";
        if (list.size() <= 0) {
            return "fail";
        }
        String batchId = UUIDGenerator.uuid();
        for (int i = 0; i < list.size(); i++) {
            List<String> list1 = (List) list.get(i);
            String topicId = list1.get(0);
            String answer = list1.get(1);
            TestPaperScoreDO scoreDO = new TestPaperScoreDO();
            //先查询对应的题目
            TestPaperTopicDO topicDO = this.topicService.get(topicId);
            if (StringUtils.isEmpty(testTypeId)) {
                testTypeId = topicDO.getTestPaperTypeId();
            }
            //通过题目查询对应的答案
            TestPaperAnswerDO answerDO = answerService.queryByTopicId(topicId);
            //判断答案是否正确
            if (answer.equals(answerDO.getTestPaperAnswer())) {
                scoreDO.setIsture(1);
                //计算对应题会得到的分数，这里只是以单项选择题为例
                scoreDO.setScore(topicDO.getTestPaperNumber());
            } else {
                scoreDO.setIsture(0);
                scoreDO.setScore(0);
            }
            //插入对应题的id
            scoreDO.setTopicId(topicDO.getId());
            scoreDO.setTestTypeId(testTypeId);
            scoreDO.setBatchId(batchId);
            scoreDO.setOperator(operator);
            scoreDO.setYourAnswer(answer);
            this.scoreService.save(scoreDO);

        }
        //计算总分
        int sumScore = this.scoreService.getSumScore(batchId);
        return "得分为" + sumScore;
    }

    @Override
    public List<TestPaperTypeDO> queryRecord(Map<String, Object> map) {
        return typeExpDao.queryRecord(map);
    }

    @Override
    public List<TestPaperTypeExpDO> querRecordDetial( Map<String, Object> map) {
        return typeExpDao.querRecordDetial(map);
    }

    /**
     * @return java.lang.String
     * @Author ZQ
     * @Description // 转换为String
     * @Date 2019/4/13 15:08
     * @Param [cell]
     **/
    private String getCellStringVal(Cell cell) {
        if (cell == null) {
            return "000000";
        }
        CellType cellType = cell.getCellTypeEnum();
        switch (cellType) {
            case NUMERIC:
                cell.setCellType(Cell.CELL_TYPE_STRING);
                return cell.getStringCellValue();
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "000000";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return StringUtils.EMPTY;
        }
    }
}
