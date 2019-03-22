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

import com.bootdo.exercise.domain.TestPaperTopicDO;
import com.bootdo.exercise.service.TestPaperTopicService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 试题题目表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-22 18:11:47
 */
 
@Controller
@RequestMapping("/exercise/testPaperTopic")
public class TestPaperTopicController {
	@Autowired
	private TestPaperTopicService testPaperTopicService;
	
	@GetMapping()
	@RequiresPermissions("exercise:testPaperTopic:testPaperTopic")
	String TestPaperTopic(){
	    return "exercise/testPaperTopic/testPaperTopic";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exercise:testPaperTopic:testPaperTopic")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestPaperTopicDO> testPaperTopicList = testPaperTopicService.list(query);
		int total = testPaperTopicService.count(query);
		PageUtils pageUtils = new PageUtils(testPaperTopicList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exercise:testPaperTopic:add")
	String add(){
	    return "exercise/testPaperTopic/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exercise:testPaperTopic:edit")
	String edit(@PathVariable("id") String id,Model model){
		TestPaperTopicDO testPaperTopic = testPaperTopicService.get(id);
		model.addAttribute("testPaperTopic", testPaperTopic);
	    return "exercise/testPaperTopic/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exercise:testPaperTopic:add")
	public R save( TestPaperTopicDO testPaperTopic){
		if(testPaperTopicService.save(testPaperTopic)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exercise:testPaperTopic:edit")
	public R update( TestPaperTopicDO testPaperTopic){
		testPaperTopicService.update(testPaperTopic);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperTopic:remove")
	public R remove( String id){
		if(testPaperTopicService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperTopic:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		testPaperTopicService.batchRemove(ids);
		return R.ok();
	}
	
}
