package com.bootdo.client;

import com.bootdo.actionservice.TestPaperActionService;

/**
 * 为以后的微服务架构做铺垫,(实现集群负载均衡)
 */
public interface TestPaperClient extends TestPaperActionService {
}
