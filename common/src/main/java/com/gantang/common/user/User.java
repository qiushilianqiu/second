package com.gantang.common.user;
import lombok.Data;

import java.io.Serializable;

/*
 * @Title(标题): User
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @Title(标题):
 * @author sl.qiu
 * @date 2020/4/23 11:56
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  实体类
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
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id; // id

    private String ystCode; // 一事通id

    private String card; // 一事通id

    private String mobilephone; // 手机号码

    private int userType; // 用户类型

    private String flag; // 用户状态

    private int orgId; // 用户Id
    private String password;
    private String icon;
    private String infoId; // 用户信息表id（员工信息、供应商信息）
    private String userName; // 用户姓名
    private String[] authorityList; // 权限列表
    private String isinit; // 权限列表
    private String ip; // 登录Ip
    private String loginType; // pc=pc/wc=wc
    private String path; // pc=pc/wc=wc
    private String openid;
}
