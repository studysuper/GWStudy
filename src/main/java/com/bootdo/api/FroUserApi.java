package com.bootdo.api;

import com.bootdo.common.utils.R;
import com.bootdo.front.domain.FroUserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/frouser")
public interface FroUserApi {

    /**
     * 前端注册功能
     */
    @PostMapping(value = "/register")
    public R register(FroUserDO froUser);
}
