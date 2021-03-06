package com.gantang.interfaces.service;

import com.baomidou.mybatisplus.service.IService;
import com.gantang.entity.test.Test;

import java.util.List;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.management.service.user
 * @ClassName(类名称): UserService
 * @Title(标题): 用于测试mybatispils是否起作用
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/24 17:10
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
public interface TestService extends IService<Test> {
    /**
     * TODO: 查询
    * @param test
     * @return java.util.List<com.gantang.entity.test.Test>
     * @author  sl.qiu
     * @date  2020/4/24 17:26
     */
    List<Test> queryList(Test test)throws Exception;
}
