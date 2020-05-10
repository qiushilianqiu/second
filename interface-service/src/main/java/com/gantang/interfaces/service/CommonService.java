package com.gantang.interfaces.service;

import com.gantang.common.result.Result;
import com.gantang.core.service.BaseService;

import javax.jms.Topic;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * @Title(标题):  公共方法管理端
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @Title(标题):
 * @author sl.qiu
 * @date 2020/4/24 16:20
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  工具类
 * TODO(这里描述这个文件做什么 – 可选)
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
public interface CommonService extends BaseService {

    List<Map<String, Object>> getDictionList(Map<String, String> map);

    List<Map<String, Object>> getEveryTableList(Map<String, String> map);

    /**
     * TODO:发送消息队列
     *
     * @param queueName
     * @param message
     * @return com.gantang.common.result.Result
     * @author sl.qiu
     * @date 2020/5/7 12:24
     */
    Result sendQueueMessage(String queueName, String message);

    /**
     * TODO:发送消息主题
     *
     * @param topic
     * @param message
     * @return com.gantang.common.result.Result
     * @author sl.qiu
     * @date 2020/5/7 12:24
     */
    Result sendTopicMessage(Topic topic, String message);
    void testTx();
}
