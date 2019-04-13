package com.bootdo.exercise.service.impl;

import com.bootdo.common.utils.ConstantUtil;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.common.utils.UUIDGenerator;
import com.bootdo.exercise.dao.TestPaperTypeDao;
import com.bootdo.exercise.dao.TestPaperTypeExpDao;
import com.bootdo.exercise.domain.*;
import com.bootdo.exercise.service.TestPaperAnswerService;
import com.bootdo.exercise.service.TestPaperOptionService;
import com.bootdo.exercise.service.TestPaperTopicService;
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
import com.bootdo.exercise.service.TestPaperTypeService;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    public TestPaperTypeServiceImpl(TestPaperTopicService topicService,
                                    TestPaperOptionService optionService,
                                    TestPaperAnswerService answerService,
                                    MaxNoService maxNoService, TestPaperTypeDao typeDao, TestPaperTypeExpDao typeExpDao) {
        this.topicService = topicService;
        this.optionService = optionService;
        this.answerService = answerService;
        this.maxNoService = maxNoService;
        this.typeExpDao = typeExpDao;
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
     * @Description // 转换为String
     * @Date 2019/4/13 15:08
     * @Param [cell]
     **/
    private String getCellStringVal(Cell cell) {
        if (cell == null) {
            return "#######";
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
                return "";
            case ERROR:
                return String.valueOf(cell.getErrorCellValue());
            default:
                return StringUtils.EMPTY;
        }
    }
}
