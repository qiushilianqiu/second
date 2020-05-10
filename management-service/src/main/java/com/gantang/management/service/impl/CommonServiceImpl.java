package com.gantang.management.service.impl;

import com.gantang.core.service.impl.BsaeServiceImpl;
import com.gantang.management.mapper.CommonMapper;
import com.gantang.management.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
public class CommonServiceImpl extends BsaeServiceImpl  implements CommonService {

    @Autowired
    CommonMapper commonMapper;

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
}
