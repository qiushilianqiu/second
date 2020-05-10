package com.gantang.interfaces.config;


import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.interfaces.config
 * @ClassName(类名称): commuserQueueFive 消费者
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/5/8 15:19
 * @version(版本): V1.0
 * @Copyright(版权): www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):该类的具体作用 注意：本内容仅限于甘棠公司内部传阅，禁止外泄以及用于其他的商业目的
 * ————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * ——————————————————————————————————————
 */
@Component
public class CommuserQueue {

    private Logger log = LoggerFactory.getLogger(getClass());

    // 与发布者开发分支
    // 与发布者destination 一致
    @JmsListener(destination = "firstQuenu", containerFactory = "queueListener_5")
    public void receiveQueueFive(Message message, Session session) throws JMSException {
        try {
            ObjectMessage objectMessage = (ObjectMessage)message;
            log.info("=======接收到消息=={}", message);
            //如果执行了这个，那么即使后面的代码抛出了异常在catch块中执行session.recover()方法也不会重试，所以
            //这个方法要放在最后执行
            int i=10/0;
            objectMessage.acknowledge();
        } catch (Exception e) {
            e.printStackTrace();
            session.recover();
            System.out.println("1312");
        }

        System.out.println("消费者接受消息:" + message);
    }

}
