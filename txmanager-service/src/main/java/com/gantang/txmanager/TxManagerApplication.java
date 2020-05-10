package com.gantang.txmanager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagerServer
public class TxManagerApplication {
    private  static Logger log = LoggerFactory.getLogger(TxManagerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(TxManagerApplication.class, args);
        log.info("甘棠分布式事务服务管理系统启动！！");
    }
}
