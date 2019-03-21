package com.bootdo.actionservice;

import com.bootdo.common.utils.R;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/frouser")
public interface FroUserActionService {

    /**
     * 前端注册功能
     */
    @PostMapping(value = "/register")
    public R register(FroUserDO froUser);
}
