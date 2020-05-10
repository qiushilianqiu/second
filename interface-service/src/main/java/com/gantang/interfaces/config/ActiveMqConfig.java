package com.gantang.interfaces.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.interfaces.config
 * @ClassName(类名称): activeMqConfig 消息中间件相关配置
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/5/7 12:04
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
@Configuration
public class ActiveMqConfig {


    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(username, password, brokerUrl);
    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        return new JmsMessagingTemplate(connectionFactory());
    }

    /**
     * TODO:  在Queue模式中，对消息的监听需要对containerFactory进行配置
     *
     * @param connectionFactory
     * @return org.springframework.jms.config.JmsListenerContainerFactory<?>
     * @author sl.qiu
     * @date 2020/5/8 15:06
     */
    @Bean("queueListener_5")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        ActiveMQConnectionFactory actConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //重发次数
        redeliveryPolicy.setMaximumRedeliveries(5);
        ;//重发时间间隔
        redeliveryPolicy.setInitialRedeliveryDelay(0);
        redeliveryPolicy.setRedeliveryDelay(2000L);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(true);
        // 将重发策略设置到C
        actConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        DefaultJmsListenerContainerFactory listener  = new DefaultJmsListenerContainerFactory();

        listener.setConnectionFactory(actConnectionFactory);
//        listener.setConcurrency("1-10");//设置连接数
//        listener.setRecoveryInterval(1000L);//重连间隔时间
        listener.setSessionAcknowledgeMode(4);

        return listener;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory factory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //设置持久化，1 非持久， 2 持久化
        jmsTemplate.setDeliveryMode(2);
        jmsTemplate.setConnectionFactory(factory);
            /**
               SESSION_TRANSACTED = 0  事物提交并确认
               AUTO_ACKNOWLEDGE = 1  自动确认
               CLIENT_ACKNOWLEDGE = 2  客户端手动确认   
               DUPS_OK_ACKNOWLEDGE = 3  自动批量确认
               INDIVIDUAL_ACKNOWLEDGE = 4  单条消息确认 activemq 独有
             */
        //消息确认模式
        jmsTemplate.setSessionAcknowledgeMode(4);
        return jmsTemplate;
    }

    /**
     * TODO:  在Queue模式中，对消息的监听需要对containerFactory进行配置
     *
     * @return org.springframework.jms.config.JmsListenerContainerFactory<?>
     * @author sl.qiu
     * @date 2020/5/8 15:06
     */
    @Bean("queueListener_24")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory() {
        ActiveMQConnectionFactory actConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerUrl);
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //是否在每次失败重发是，增长等待时间
        redeliveryPolicy.setUseExponentialBackOff(true);
        //设置重发最大拖延时间，-1 表示没有拖延，只有setUseExponentialBackOff(true)时生效
        redeliveryPolicy.setMaximumRedeliveryDelay(-1);
        //重发次数
        redeliveryPolicy.setMaximumRedeliveries(5);
        //重发时间间隔
        redeliveryPolicy.setInitialRedeliveryDelay(1000L);
        //第一次失败后重发前等待500毫秒，第二次500*2，依次递增
        redeliveryPolicy.setBackOffMultiplier(2);
        //是否避免消息碰撞
        redeliveryPolicy.setUseCollisionAvoidance(true);
        // 将重发策略设置到C
        actConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        DefaultJmsListenerContainerFactory listener  = new DefaultJmsListenerContainerFactory();
        listener.setConnectionFactory(actConnectionFactory);
        //设置连接数
        listener.setConcurrency("1-10");
        //重连间隔时间
//        listener.setRecoveryInterval(1000L);
//        listener.setSessionAcknowledgeMode(4);
        listener .setPubSubDomain(false);
        return listener ;
    }
//    //在Topic模式中，对消息的监听需要对containerFactory进行配置
//    @Bean("topicListener")
//    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setPubSubDomain(true);
//        return factory;
//    }
}
