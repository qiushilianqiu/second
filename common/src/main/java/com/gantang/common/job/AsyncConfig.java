package com.gantang.common.job;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.common.job
 * @ClassName(类名称):AsyncConfig
 * @Title(标题):  AsyncConfig.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司 sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年12月18日 上午11:18:53   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述): 在SpringBoot项目中一般使用config配置类的方式添加配置，所以新建一个AsyncConfig类  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 *—————————————————————————————————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人: 
 *    修改原因：
 *              
 *—————————————————————————————————————————————————————————————————
 */
//@Configuration
////表明该类是一个配置类
//@EnableAsync
//开启异步事件的支持
public class AsyncConfig {
     /**
      * 此处成员变量应该使用@Value从配置中读取
     */
	/**
	 * 线程池维护线程的最小数量.
	 */
    private int corePoolSize = 10;
    /**
     * 线程池维护线程的最大数量
     */
    private int maxPoolSize = 200;
    /**
     * 队列最大长度
     */
    private int queueCapacity = 10;
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();
        return executor;
    }
}
