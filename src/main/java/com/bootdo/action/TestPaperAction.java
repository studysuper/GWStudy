package com.bootdo.action;

import com.bootdo.actionservice.TestPaperActionService;
import com.bootdo.common.utils.Query;
import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import com.bootdo.exercise.service.TestPaperTypeService;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
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

    @Override
    public void createTestPaper(@RequestBody Map<String, Object> map, HttpServletResponse response) throws IOException {
        Query query = new Query(map);
        List<TestPaperTypeExpDO> testPaperTypeExpDOS = this.paperTypeService.listExp(query);
        String filePath = createWordFile(testPaperTypeExpDOS);
        downFile(filePath, response);
    }

    /**
     * @return void
     * @Author ZQ
     * @Description //文件下载
     * @Date 2019/4/23 20:22
     * @Param [file, response]
     **/
    private String downFile(String filePath, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType("application/x-download");
       /* String filedownload = "";
        String filedisplay = "";
        filedisplay = URLEncoder.encode(filedisplay, "UTF-8");*/
        response.addHeader("Content-Disposition", "attachment;filename=" + "123.docx");
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            outp = response.getOutputStream();
            in = new FileInputStream(filePath);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (outp != null) {
                try {
                    outp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outp = null;
            }
        }
        return null;
    }

    /**
     * @return void
     * @Author ZQ
     * @Description //生成word文档
     * @Date 2019/4/23 19:08
     * @Param [testPaperTypeExpDOS]
     **/
    private String createWordFile(List<TestPaperTypeExpDO> testPaperTypeExpDOS) throws IOException {
        XWPFDocument document = new XWPFDocument();
        TestPaperTypeExpDO testPaperTypeDO = testPaperTypeExpDOS.get(0);
        String fileName = testPaperTypeDO.getExerciseTitle();
        int totolTime = testPaperTypeDO.getExerciseTime();//总时间
        int totolNumber = testPaperTypeDO.getExerciseNumber();//总分数

        String path = "D:\\wordFile\\";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = path + fileName + ".docx";
        File file = new File(filePath);
        if (!file.exists()) {
            file.delete();
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);

        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();

        titleParagraphRun.setText(fileName);
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);
        titleParagraphRun.setBold(true);

        //总时间
        XWPFParagraph totalTimeRaph = document.createParagraph();
        XWPFRun totalTimeRun = totalTimeRaph.createRun();
        totalTimeRaph.setAlignment(ParagraphAlignment.CENTER);//设置居中
        totalTimeRun.setBold(true);
        totalTimeRun.setText("总时间:" + totolTime + "分钟   ");
        totalTimeRun.setColor("000000");
        totalTimeRun.setFontSize(12);

        //总分数
        XWPFParagraph totalNumerRaph = document.createParagraph();
        XWPFRun totalNumerRun = totalTimeRaph.createRun();
        totalNumerRaph.setAlignment(ParagraphAlignment.CENTER);//设置居中
        totalNumerRun.setBold(true);
        totalNumerRun.setText("总分数:" + totolNumber + "分");
        totalNumerRun.setColor("000000");
        totalNumerRun.setFontSize(12);

        int count = 1;
        for (TestPaperTypeExpDO itemdo : testPaperTypeExpDOS) {
            String topic = itemdo.getTestPaperTopic();//题干
            int pageNumber = itemdo.getPaperNumber();//每道题分数

            //题目
            XWPFParagraph topicimeRaph = document.createParagraph();
            XWPFRun topicRun = topicimeRaph.createRun();
            topicRun.setText(count + ": " + topic + "(" + pageNumber + ")分");
            topicRun.setColor("000000");
            topicRun.setFontSize(10);

            //换行
            XWPFParagraph paragraph2 = document.createParagraph();
            XWPFRun paragraphRun2 = paragraph2.createRun();
            paragraphRun2.setText("\r");

            //选项A
            XWPFParagraph optionARaph = document.createParagraph();
            XWPFRun optionARun = optionARaph.createRun();
            optionARun.setText("A: " + itemdo.getTestPaperOptionA());
            optionARun.setColor("000000");
            optionARun.setFontSize(10);

            XWPFParagraph paragraph3 = document.createParagraph();
            XWPFRun paragraphRun3 = paragraph3.createRun();
            paragraphRun3.setText("\r");

            //选项B
            XWPFParagraph optionBRaph = document.createParagraph();
            XWPFRun optionBRun = optionBRaph.createRun();
            optionBRun.setText("B: " + itemdo.getTestPaperOptionB());
            optionBRun.setColor("000000");
            optionBRun.setFontSize(10);

            XWPFParagraph paragraph4 = document.createParagraph();
            XWPFRun paragraphRun4 = paragraph3.createRun();
            paragraphRun4.setText("\r");

            //选项C
            XWPFParagraph optionCRaph = document.createParagraph();
            XWPFRun optionCRun = optionCRaph.createRun();
            optionCRun.setText("C: " + itemdo.getTestPaperOptionC());
            optionCRun.setColor("000000");
            optionCRun.setFontSize(10);

            XWPFParagraph paragraph5 = document.createParagraph();
            XWPFRun paragraphRun5 = paragraph5.createRun();
            paragraphRun5.setText("\r");

            //选项D
            XWPFParagraph optionDRaph = document.createParagraph();
            XWPFRun optionDRun = optionDRaph.createRun();
            optionDRun.setText("D: " + itemdo.getTestPaperOptionD());
            optionDRun.setColor("000000");
            optionDRun.setFontSize(10);

            XWPFParagraph paragraph6 = document.createParagraph();
            XWPFRun paragraphRun6 = paragraph6.createRun();
            paragraphRun6.setText("\r");

            //答案
            XWPFParagraph answerRaph = document.createParagraph();
            XWPFRun answerRun = answerRaph.createRun();
            answerRun.setBold(true);
            answerRun.setText("答案为： " + itemdo.getTestPaperAnswer());
            answerRun.setColor("000000");
            answerRun.setFontSize(10);

            XWPFParagraph paragraph7 = document.createParagraph();
            XWPFRun paragraphRun7 = paragraph7.createRun();
            paragraphRun7.setText("\r");


            count++;
        }
        document.write(out);
        out.close();
        return filePath;
    }
}
