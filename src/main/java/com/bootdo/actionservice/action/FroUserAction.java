package com.bootdo.actionservice.action;

import com.bootdo.actionservice.FroUserActionService;
import com.bootdo.common.utils.R;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口的Action类，相当于本系统原来的Controller
 */
@RestController
public class FroUserAction implements FroUserActionService {
    @Override
    public R register(@RequestBody FroUserDO froUser) {
        System.out.println("成功");
        return R.ok("可算搞定了");
    }
}
