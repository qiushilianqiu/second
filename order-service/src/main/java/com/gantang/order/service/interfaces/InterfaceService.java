package com.gantang.order.service.interfaces;

import com.gantang.common.result.Result;
import com.gantang.core.globalconfig.FeignConfig;
import com.gantang.order.configer.UserServerFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.order.service.interfaces
 * @ClassName(类名称): orderService
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/26 15:36
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
@FeignClient(value = "interface-service",configuration = FeignConfig.class, fallback = UserServerFeignClientFallback.class) //这里的name对应调用服务的spring.applicatoin.name
//@FeignClient(value = "interface-service",fallback = UserServerFeignClientFallback.class) //这里的name对应调用服务的spring.applicatoin.name
public interface InterfaceService {
    @RequestMapping(value = "common/orderInterface")
    Result orderInterface( @RequestParam("id") String id);
}
