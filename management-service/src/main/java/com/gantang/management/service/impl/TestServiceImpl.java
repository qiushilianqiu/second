package com.gantang.management.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gantang.entity.test.Test;
import com.gantang.management.mapper.TestMapper;
import com.gantang.management.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.management.service.impl
 * @ClassName(类名称): TestServiceImpl
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/24 17:20
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
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Override
    public List<Test> queryList(Test test) throws Exception {
        EntityWrapper<Test> wrapper = new EntityWrapper<>();
        wrapper.setEntity(test);
        List<Test> selectList = this.selectList(wrapper);
        return selectList;
    }
}
