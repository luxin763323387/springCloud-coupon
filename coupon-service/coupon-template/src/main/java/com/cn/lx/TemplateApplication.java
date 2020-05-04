package com.cn.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 模版启动类
 * @author StevenLu
 * @date 2020-05-04 10:54
 */
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@EnableJpaAuditing
public class TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class,args);
    }
}
