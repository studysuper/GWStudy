package com.bootdo.action;

import com.bootdo.actionservice.FroUserActionService;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.front.domain.FroUserDO;
import com.bootdo.front.service.FroUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口的Action类，相当于本系统原来的Controller
 */
@RestController
public class FroUserAction implements FroUserActionService {
    @Autowired
    private FroUserService froUserService;

    @Override
    public R register(@RequestBody FroUserDO froUser) {
        int i = froUserService.getUserName(froUser);
        if(i>0){
            return R.error("用户名已存在");
        }
        if (froUserService.saveExp(froUser) > 0) {
            return R.ok("register");
        }
        return R.error();
    }

    @Override
    public R login(@RequestBody FroUserDO forUser) {
        String msg = validate(forUser);
        if(!StringUtils.isEmpty(msg)){
            return R.error(msg);
        }
        if(froUserService.loginExp(forUser) > 0) {
            return R.ok("login");
        }
        return R.error("用户名或密码不正确");
    }

    private String validate(FroUserDO forUser) {
        if(StringUtils.isEmpty(forUser.getUserName())){
            return "用户名不能为空";
        }
        if(StringUtils.isEmpty(forUser.getPassword())){
           return  "密码不能为空";
        }
        return null;
    }
}
