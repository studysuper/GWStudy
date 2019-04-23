package com.bootdo.actionservice;

import com.bootdo.common.utils.R;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.*;
/**
 * @Author ZQ
 * @Description //提供用户接口
 * @Date 2019/4/23 14:37
 * @Param 
 * @return 
 **/
@RequestMapping("/api/frouser")
public interface FroUserActionService {

    /**
     * 前端注册功能
     */
    @PostMapping(value = "/register")
    public R register(FroUserDO froUser);

    /**
     * 前端登录功能
     */
    @PostMapping(value = "/login")
    public R login(FroUserDO froUser);
}
