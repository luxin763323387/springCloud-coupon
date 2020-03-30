package com.cn.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * 网关启动入口
 * 1. @EnableZuulProxy 标识当前的应用是 Zuul Server
 * 2. @SpringCloudApplication 组合了 SpringBoot 应用 + 服务发现 + 熔断
 * @author StevenLu
 * @date 2020-03-22 13:37
 */
@SpringCloudApplication
@EnableZuulServer
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
