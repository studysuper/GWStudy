package com.bootdo.exercise.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.exercise.domain.TestPaperAnswerDO;
import com.bootdo.exercise.service.TestPaperAnswerService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 试题答案表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
 
@Controller
@RequestMapping("/exercise/testPaperAnswer")
public class TestPaperAnswerController {
	@Autowired
	private TestPaperAnswerService testPaperAnswerService;
	
	@GetMapping()
	@RequiresPermissions("exercise:testPaperAnswer:testPaperAnswer")
	String TestPaperAnswer(){
	    return "exercise/testPaperAnswer/testPaperAnswer";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exercise:testPaperAnswer:testPaperAnswer")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestPaperAnswerDO> testPaperAnswerList = testPaperAnswerService.list(query);
		int total = testPaperAnswerService.count(query);
		PageUtils pageUtils = new PageUtils(testPaperAnswerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exercise:testPaperAnswer:add")
	String add(){
	    return "exercise/testPaperAnswer/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exercise:testPaperAnswer:edit")
	String edit(@PathVariable("id") String id,Model model){
		TestPaperAnswerDO testPaperAnswer = testPaperAnswerService.get(id);
		model.addAttribute("testPaperAnswer", testPaperAnswer);
	    return "exercise/testPaperAnswer/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exercise:testPaperAnswer:add")
	public R save( TestPaperAnswerDO testPaperAnswer){
		if(testPaperAnswerService.save(testPaperAnswer)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exercise:testPaperAnswer:edit")
	public R update( TestPaperAnswerDO testPaperAnswer){
		testPaperAnswerService.update(testPaperAnswer);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperAnswer:remove")
	public R remove( String id){
		if(testPaperAnswerService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperAnswer:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		testPaperAnswerService.batchRemove(ids);
		return R.ok();
	}
	
}
