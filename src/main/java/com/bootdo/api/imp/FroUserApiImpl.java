package com.bootdo.api.imp;

import com.bootdo.api.FroUserApi;
import com.bootdo.common.utils.R;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FroUserApiImpl implements FroUserApi {
    @Override
    public R register(@RequestBody FroUserDO froUser) {
        System.out.println("成功");
        return R.ok("可算搞定了");
    }
}
