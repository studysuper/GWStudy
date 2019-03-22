package com.bootdo.sys.controller;

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

import com.bootdo.sys.domain.MaxNoDO;
import com.bootdo.sys.service.MaxNoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 系统最大号表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-22 19:12:58
 */
 
@Controller
@RequestMapping("/sys/maxNo")
public class MaxNoController {
	@Autowired
	private MaxNoService maxNoService;
	
	@GetMapping()
	@RequiresPermissions("sys:maxNo:maxNo")
	String MaxNo(){
	    return "sys/maxNo/maxNo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:maxNo:maxNo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MaxNoDO> maxNoList = maxNoService.list(query);
		int total = maxNoService.count(query);
		PageUtils pageUtils = new PageUtils(maxNoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("sys:maxNo:add")
	String add(){
	    return "sys/maxNo/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:maxNo:edit")
	String edit(@PathVariable("id") String id,Model model){
		MaxNoDO maxNo = maxNoService.get(id);
		model.addAttribute("maxNo", maxNo);
	    return "sys/maxNo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:maxNo:add")
	public R save( MaxNoDO maxNo){
		if(maxNoService.save(maxNo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:maxNo:edit")
	public R update( MaxNoDO maxNo){
		maxNoService.update(maxNo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:maxNo:remove")
	public R remove( String id){
		if(maxNoService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:maxNo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		maxNoService.batchRemove(ids);
		return R.ok();
	}
	
}
