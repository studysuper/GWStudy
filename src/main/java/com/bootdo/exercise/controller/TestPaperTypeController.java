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

import com.bootdo.exercise.domain.TestPaperTypeDO;
import com.bootdo.exercise.service.TestPaperTypeService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 试卷表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
 
@Controller
@RequestMapping("/exercise/testPaperType")
public class TestPaperTypeController {
	@Autowired
	private TestPaperTypeService testPaperTypeService;
	
	@GetMapping()
	@RequiresPermissions("exercise:testPaperType:testPaperType")
	String TestPaperType(){
	    return "exercise/testPaperType/testPaperType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exercise:testPaperType:testPaperType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestPaperTypeDO> testPaperTypeList = testPaperTypeService.list(query);
		int total = testPaperTypeService.count(query);
		PageUtils pageUtils = new PageUtils(testPaperTypeList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exercise:testPaperType:add")
	String add(){
	    return "exercise/testPaperType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exercise:testPaperType:edit")
	String edit(@PathVariable("id") String id,Model model){
		TestPaperTypeDO testPaperType = testPaperTypeService.get(id);
		model.addAttribute("testPaperType", testPaperType);
	    return "exercise/testPaperType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exercise:testPaperType:add")
	public R save( TestPaperTypeDO testPaperType){
		if(testPaperTypeService.save(testPaperType)>0){
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
	public R update( TestPaperTypeDO testPaperType){
		testPaperTypeService.update(testPaperType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperType:remove")
	public R remove( String id){
		if(testPaperTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperType:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		testPaperTypeService.batchRemove(ids);
		return R.ok();
	}
	
}
