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

import com.bootdo.exercise.domain.TestPaperOptionDO;
import com.bootdo.exercise.service.TestPaperOptionService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 试题选项表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
 
@Controller
@RequestMapping("/exercise/testPaperOption")
public class TestPaperOptionController {
	@Autowired
	private TestPaperOptionService testPaperOptionService;
	
	@GetMapping()
	@RequiresPermissions("exercise:testPaperOption:testPaperOption")
	String TestPaperOption(){
	    return "exercise/testPaperOption/testPaperOption";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("exercise:testPaperOption:testPaperOption")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestPaperOptionDO> testPaperOptionList = testPaperOptionService.list(query);
		int total = testPaperOptionService.count(query);
		PageUtils pageUtils = new PageUtils(testPaperOptionList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("exercise:testPaperOption:add")
	String add(){
	    return "exercise/testPaperOption/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("exercise:testPaperOption:edit")
	String edit(@PathVariable("id") String id,Model model){
		TestPaperOptionDO testPaperOption = testPaperOptionService.get(id);
		model.addAttribute("testPaperOption", testPaperOption);
	    return "exercise/testPaperOption/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("exercise:testPaperOption:add")
	public R save( TestPaperOptionDO testPaperOption){
		if(testPaperOptionService.save(testPaperOption)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("exercise:testPaperOption:edit")
	public R update( TestPaperOptionDO testPaperOption){
		testPaperOptionService.update(testPaperOption);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperOption:remove")
	public R remove( String id){
		if(testPaperOptionService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("exercise:testPaperOption:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		testPaperOptionService.batchRemove(ids);
		return R.ok();
	}
	
}
