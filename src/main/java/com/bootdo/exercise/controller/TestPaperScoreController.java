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

import com.bootdo.exercise.domain.TestPaperScoreDO;
import com.bootdo.exercise.service.TestPaperScoreService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author zq
 * @email 519996418
 * @date 2019-05-03 00:24:35
 */
 
@Controller
@RequestMapping("/exercise/testPaperScore")
public class TestPaperScoreController {
	@Autowired
	private TestPaperScoreService testPaperScoreService;
	
	@GetMapping()
	@RequiresPermissions("exercise:testPaperScore:testPaperScore")
	String TestPaperScore(){
	    return "exercise/testPaperScore/testPaperScore";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exercise:testPaperScore:testPaperScore")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestPaperScoreDO> testPaperScoreList = testPaperScoreService.list(query);
		int total = testPaperScoreService.count(query);
		PageUtils pageUtils = new PageUtils(testPaperScoreList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exercise:testPaperScore:add")
	String add(){
	    return "exercise/testPaperScore/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exercise:testPaperScore:edit")
	String edit(@PathVariable("id") String id,Model model){
		TestPaperScoreDO testPaperScore = testPaperScoreService.get(id);
		model.addAttribute("testPaperScore", testPaperScore);
	    return "exercise/testPaperScore/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exercise:testPaperScore:add")
	public R save( TestPaperScoreDO testPaperScore){
		if(testPaperScoreService.save(testPaperScore)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exercise:testPaperScore:edit")
	public R update( TestPaperScoreDO testPaperScore){
		testPaperScoreService.update(testPaperScore);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperScore:remove")
	public R remove( String id){
		if(testPaperScoreService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperScore:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		testPaperScoreService.batchRemove(ids);
		return R.ok();
	}
	
}
