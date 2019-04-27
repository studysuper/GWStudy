package com.bootdo.front.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.front.domain.FroUserDO;
import com.bootdo.front.service.FroUserService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 前端用户表
 *
 * @author zq
 * @email 519996418
 * @date 2019-03-20 14:44:59
 */

@Controller
@RequestMapping("/front/froUser")
public class FroUserController {
    @Autowired
    private FroUserService froUserService;


    @GetMapping()
    @RequiresPermissions("front:froUser:froUser")
    String FroUser() {
        return "front/froUser/froUser";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("front:froUser:froUser")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<FroUserDO> froUserList = froUserService.list(query);
        int total = froUserService.count(query);
        PageUtils pageUtils = new PageUtils(froUserList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("front:froUser:add")
    String add() {
        return "front/froUser/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("front:froUser:edit")
    String edit(@PathVariable("id") String id, Model model) {
        FroUserDO froUser = froUserService.get(id);
        model.addAttribute("froUser", froUser);
        return "front/froUser/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("front:froUser:add")
    public R save(FroUserDO froUser) {
        if (froUserService.saveExp(froUser) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("front:froUser:edit")
    public R update(FroUserDO froUser) {
        froUserService.update(froUser);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("front:froUser:remove")
    public R remove(String id) {
        if (froUserService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("front:froUser:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        froUserService.batchRemove(ids);
        return R.ok();
    }

}
