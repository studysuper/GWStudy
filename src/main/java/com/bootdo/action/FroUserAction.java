package com.bootdo.action;

import com.bootdo.actionservice.FroUserActionService;
import com.bootdo.common.utils.R;
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
        if (froUserService.save(froUser) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
