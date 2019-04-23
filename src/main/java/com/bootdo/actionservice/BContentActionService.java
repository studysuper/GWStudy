package com.bootdo.actionservice;

import com.bootdo.blog.domain.ContentDO;
import com.bootdo.common.utils.R;
import com.bootdo.front.domain.FroUserDO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @Author ZQ
 * @Description //文章管理相关接口
 * @Date 2019/4/23 14:37
 * @Param
 * @return
 **/
@RequestMapping("/api/bContent")
public interface BContentActionService {

    /**
     * @return java.util.List<com.bootdo.blog.domain.ContentDO>
     * @Author ZQ
     * @Description //查询文章管理list
     * @Date 2019/4/23 14:40
     * @Param [map]
     **/
    @PostMapping("/queryContendList")
    List<ContentDO> queryContendList(Map<String, Object> map);

    @PostMapping("/queryContendById")
    ContentDO queryContendById(Map<String, Object> map);
}
