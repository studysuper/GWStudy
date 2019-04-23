package com.bootdo.action;

import com.bootdo.actionservice.BContentActionService;
import com.bootdo.blog.domain.ContentDO;
import com.bootdo.blog.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 接口的Action类，相当于本系统原来的Controller
 */
@RestController
public class BContentAction implements BContentActionService {

    private final ContentService contentService;

    @Autowired
    public BContentAction(ContentService contentService) {
        this.contentService = contentService;
    }

    @Override
    public List<ContentDO> queryContendList(@RequestBody Map<String, Object> map) {
        return contentService.list(map);
    }

    @Override
    public ContentDO queryContendById(@RequestBody Map<String, Object> map) {
        return (ContentDO) contentService.get(Long.valueOf(""+map.get("id")));
    }
}
