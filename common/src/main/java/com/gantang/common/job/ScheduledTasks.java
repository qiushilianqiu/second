package com.gantang.common.job;


import com.gantang.common.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 *
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.controller
 * @ClassName(类名称):scheduledTasks
 * @Title(标题): scheduledTasks.java
 * @see(与该类相关联的类):
 * @author(作者): 深圳市甘棠餐饮集团有限公司 sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2018年12月18日 上午10:59:53
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述): 定时器任务
 * TODO(这里描述这个文件做什么 – 可选)
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * —————————————————————————————————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * —————————————————————————————————————————————————————————————————
 */
@Component
@EnableScheduling
@Async
public class ScheduledTasks {
    private static Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    RedisUtil redisUtil;


    /**
     * 网点建设项目到期提醒
     *
     * @param
     * @return void
     * @Description:
     * @author qsl
     * @date 2018年6月5日下午3:46:58
     */
    //@SysLog("执行失败-网点建设项目到期提醒")
//    @Scheduled(cron = "0 0 10 * * ? ")
//    public void notifyProjectProcess() {
//        try {
//            guantTaskService.notifyProjectProcess();
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            //插入日志表
//            //SystemLog systemLog = new SystemLog("1", "/api/ScheduledTasks/ScheduledTasks", "127.0.0.1", "执行失败-网点建设项目到期提醒", e.getMessage().substring(800));
//            //userMapper.saveLog(systemLog);
//        }
//    }





}
