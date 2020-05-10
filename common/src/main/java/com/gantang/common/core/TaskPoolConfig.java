package com.gantang.common.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//@EnableAsync
//@Configuration
public class TaskPoolConfig {
    private static final Logger LOG = LoggerFactory.getLogger(TaskPoolConfig.class);
//    @Bean("taskExcutor")
    public Executor taskExcutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); //核心线程数
        executor.setMaxPoolSize(20); //最大线程数
        executor.setQueueCapacity(6000); //队列缓冲数
        executor.setKeepAliveSeconds(60); //线程允许的空闲时间
        executor.setThreadNamePrefix("exam-task-"); //线程名称前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //线程池对拒绝任务的处理
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        LOG.info("异步结束...");
        return executor;
    }

}
