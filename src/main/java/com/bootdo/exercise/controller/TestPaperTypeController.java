package com.bootdo.exercise.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.exercise.domain.TestPaperTypeExpDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.service.TestPaperTypeService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 试卷表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */

@Controller
@RequestMapping("/exercise/testPaperType")
public class TestPaperTypeController {
    private final TestPaperTypeService testPaperTypeService;
    private final FileService sysFileService;
    private final BootdoConfig bootdoConfig;

    @Autowired
    public TestPaperTypeController(TestPaperTypeService testPaperTypeService,
                                   FileService sysFileService,
                                   BootdoConfig bootdoConfig) {
        this.testPaperTypeService = testPaperTypeService;
        this.sysFileService = sysFileService;
        this.bootdoConfig = bootdoConfig;
    }

    @GetMapping()
    @RequiresPermissions("exercise:testPaperType:testPaperType")
    String TestPaperType() {
        return "exercise/testPaperType/testPaperType";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("exercise:testPaperType:testPaperType")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<TestPaperTypeExpDO> testPaperTypeList = testPaperTypeService.listExp(query);
        int total = testPaperTypeService.countExp(query);
        PageUtils pageUtils = new PageUtils(testPaperTypeList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("exercise:testPaperType:add")
    String add() {
        return "exercise/testPaperType/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("exercise:testPaperType:edit")
    String edit(@PathVariable("id") String id, Model model) {
        TestPaperTypeDO testPaperType = testPaperTypeService.get(id);
        model.addAttribute("testPaperType", testPaperType);
        return "exercise/testPaperType/edit";
    }

    /**
     * 上传文件并进行解析保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("exercise:testPaperType:add")
    public R save(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id) || id.equals("undefined")) {
            return R.error("请选择要导入的试卷");
        }
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
        try {
            FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return R.error();
        }

        if (sysFileService.save(sysFile) > 0) {
            //解析excel中的数据
            this.testPaperTypeService.doExcel(file, id);
            return R.ok().put("fileName", sysFile.getUrl());
        }
        return R.error();


    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/saveType")
    @RequiresPermissions("exercise:testPaperType:add")
    public R saveType(TestPaperTypeDO testPaperType) {
        if (testPaperTypeService.saveExp(testPaperType) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("exercise:testPaperType:edit")
    public R update(TestPaperTypeDO testPaperType) {
        testPaperTypeService.update(testPaperType);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("exercise:testPaperType:remove")
    public R remove(String id) {
        String[] strs = new String[1];
        strs[0] = id;
        if (testPaperTypeService.batchRemoveExp(strs) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("exercise:testPaperType:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        testPaperTypeService.batchRemoveExp(ids);
        return R.ok();
    }

}
