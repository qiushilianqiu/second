package com.gantang.entity.organization;

import lombok.Data;

import java.util.List;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.employee
 * @ClassName(类名称):OrganizedMenu
 * @Title(标题):  OrganizedMenu.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司 sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年8月28日 上午9:17:31   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  动态生成组织架构表
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
public class OrganizedMenu {
    // 菜单id
    private String id;
    // 菜单名称
	private String name;
	// 菜单名称
	private String indexs;
	// 父菜单id
    private String parentId;
    // 菜单url
    private String url;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int order;
    // 是否最低节点
    private String isLeaf;
    // 子菜单
    private List<OrganizedMenu> childMenus;
    // 
	private String tel;
	private String fax;
	private String address;
	private String printcipal;
	private String remark;
	private String costCenter;
	private String orgNames;

}
