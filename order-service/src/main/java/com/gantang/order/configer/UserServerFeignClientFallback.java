package com.gantang.order.configer;

import com.codingapi.txlcn.tc.support.DTXUserControls;
import com.codingapi.txlcn.tracing.TracingContext;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import com.gantang.order.service.interfaces.InterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * @Title(标题):  自定义服务间调用失败后补偿机制
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @Title(标题):
 * @author sl.qiu
 * @date 2020/4/27 12:46
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  工具类
 * TODO(这里描述这个文件做什么 – 可选)
 * 注意：本内容仅限于汇合发展有限公司内部传阅，禁止外泄以及用于其他的商业项目
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
@Component
public class UserServerFeignClientFallback implements InterfaceService {
    private  static Logger log = LoggerFactory.getLogger(UserServerFeignClientFallback.class);
    @Override
    public Result orderInterface(String id) {
        log.info("Feign调用失败，事务回滚！");
        DTXUserControls.rollbackGroup(TracingContext.tracing().groupId());
        return ResultGenerator.genFailInterfaceResult("第三方接口报错或者第三方服务停止");
//        throw  new MyInterfaceException();

    }
}
