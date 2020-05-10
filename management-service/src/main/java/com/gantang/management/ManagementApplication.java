package com.gantang.management;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.management
 * @ClassName(类名称): ManagementApplication
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/23 16:27
 * @version(版本): V1.0
 * @Copyright(版权): www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):该类的具体作用 注意：本内容仅限于甘棠公司内部传阅，禁止外泄以及用于其他的商业目的的
 * ————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * ——————————————————————————————————————
 */


@SpringBootApplication
@MapperScan(basePackages = {"com.gantang.management.mapper","com.gantang.core.mapper"})
@ComponentScan(basePackages = {"com.gantang.management", "com.gantang.common", "com.gantang.core"})
//@EnableTransactionManagement
@EnableDiscoveryClient
//@EnableDistributedTransaction
public class ManagementApplication {
    private  static Logger log = LoggerFactory.getLogger(ManagementApplication.class);
    public static void main(String[] args){
        SpringApplication.run(ManagementApplication.class, args);
        log.info("甘棠后台管理系统启动！！");
    }
}
