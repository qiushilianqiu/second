package com.gantang.eureka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

    private static final Logger LOG = LoggerFactory.getLogger(EurekaApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
        LOG.info("甘棠微服务注册中心启动");
    }
}
