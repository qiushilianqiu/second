package com.gantang.interfaces.service.impl;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import com.gantang.common.util.F;
import com.gantang.core.service.impl.BsaeServiceImpl;
import com.gantang.interfaces.mapper.CommonMapper;
import com.gantang.interfaces.service.CommonService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.management.service.impl
 * @ClassName(类名称): commonServiceImpl
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/24 16:19
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
@Service

public class CommonServiceImpl extends BsaeServiceImpl implements CommonService {



    @Autowired
    CommonMapper commonMapper;
    @Autowired
    ConnectionFactory connectionFactory;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    @LcnTransaction
    @Transactional
    public void testTx() {

        this.insert("sys_user", new F("name", "wangwu"),
                new F("car_id", "132"));

//        if (true) {
//            throw new MyInterfaceException("接口错误");
//        }
    }

    @Override
    public List<Map<String, Object>> getDictionList(Map<String, String> map) {
        List<Map<String, Object>> dIctionList = commonMapper.getDictionList(map);

        return dIctionList;
    }

    @Override
    public List<Map<String, Object>> getEveryTableList(Map<String, String> map) {
        List<Map<String, Object>> list = commonMapper.getEveryTableList(map);
        return list;
    }
    /**
     * TODO:发送消息队列
     *
     * @param queueName
     * @param message
     * @return com.gantang.common.result.Result
     * @author sl.qiu
     * @date 2020/5/7 12:24
     */
    @Override
    public Result sendQueueMessage(String queueName, String message) {
        ActiveMQQueue activeMqQueue = new ActiveMQQueue(queueName);
        jmsTemplate.send(queueName,session -> session.createObjectMessage(message));
        return ResultGenerator.genSuccessResult("成功插入数据到队列中去!");
    }
    /**
     * TODO:发送消息主题
     *
     * @param topic
     * @param message
     * @return com.gantang.common.result.Result
     * @author sl.qiu
     * @date 2020/5/7 12:24
     */
    @Override
    public Result sendTopicMessage(Topic topic, String message) {
        return null;
    }
}
