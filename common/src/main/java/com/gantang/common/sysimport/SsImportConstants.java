/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):SsImportEnum
 * @Title(标题):  SsImportEnum.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月24日 上午9:51:09   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    TODO(用一句话描述该文件做什么)  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业目的
 *————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人: 
 *    修改原因：
 *              
 *——————————————————————————————————————
 */
package com.gantang.common.sysimport;

/**
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * 
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):SsImportEnum @Title(标题):
 *                              SsImportEnum.java @see(与该类相关联的类): @author(作者):
 *                              sl.qiu
 * @since: JDK1.8 @date(创建日期): 2018年7月24日 上午9:51:09 @version(版本):
 *         V1.0 @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights
 *         reserved. @Description(描述): 上传枚举类型 TODO(这里描述这个文件做什么 – 可选)
 *         注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目 ==== All rights Reserved,
 *         Designed By www.gantang.com.cn ====
 *         —————————————————————————————————————————————————————————————————
 *         修改记录 修改者： 修改时间： 复审人: 修改原因：
 * 
 *         —————————————————————————————————————————————————————————————————
 */
public class SsImportConstants {
	// ..平台类型枚举
    public enum SsImportEnumType {
        // 台账导入
		IN_LEASE_ACCOUNT,
		IN_LEASE_ACCOUNT_AUTO,
		// 合同信息导入
		IN_LEASE_CONTRACT,
		// 自有物业
		IN_OWN_PROPERTY,
		// 员工信息
		IN_PERSONNEL,
		// 宿舍入住
		IN_DORM_CHECKIN,
		// 小区导入
		IN_DORMITORY,
		// 合同导入自助银行
		IN_LEASE_CONTRACT_AUTO,
		// 宿舍及床位导入
		IN_DORMBUNKS,
    	//清洁
		IN_CLEAN,
    	//物业
		IN_PROPERTY,
    	//办公用品
		IN_DAILYPRODUCT,
		//组织架构
		IN_ORGANIZATION,
		//物料
		IN_MATERIALS,
    	//宿舍报修
		IN_DORMITORYSS,
		//二维码信息导入
		IN_QC_CODE,
		//设备维修及耗材领用
		IN_CONSUMABLES,
		//座位与电话申请
		IN_SEATPHONE,
		//电费管理入账单导入
		IN_ELECRTRIC_ACCOUNT,
		//水费管理入账单导入
		IN_WATER_ACCOUNT,
		//周转房入账单导入
		IN_OTHER_ACCOUNT,
		//管理费入账单导入
		IN_PROPERTY_ACCOUNT,
		//网点自助银行数据导入
		IN_DOT_SELF_HELP,
		//网点信息导入
		IN_DOTINFO;
		public static SsImportEnumType toType(String str) {
			try {
				return valueOf(str);
			} catch (Exception ex) {
				return IN_LEASE_ACCOUNT;
			}
		}
	}

	// ..平台类型枚举
    public enum SsExportEnumType {
        // 台账导出
		OUT_LEASE_ACCOUNT,
		OUT_LEASE_ACCOUNT_AUTO,
		// 租金信息
		OUT_RENT,
		// 租金自动
		OUT_RENT_AUTO,
		//租金记录
		OUT_RENT_QUARTER,
		//租金季度自助
		OUT_RENT_QUARTER_AUTO,
		// 导出合同信息
		OUT_LEASE_CONTRACT,
		// 导出自助合同信息
		OUT_LEASE_CONTRACT_AUTO,
		// 导出自有物业
		OUT_OWNER_PROPERTY,
		// 导出公司架构
		OUT_ORGANIZATION,
		// 床位
		OUT_CHECK,
		// 住宿人员信息导出
		OUT_PERSON_MESS,
		// 员工信息导出
		OUT_PERSON,
		//调宿记录导出
		OUT_CHECK_RECORD,
		//巡检积分导出
		OUT_INSPECTION,
		// 宿舍导出
		OUT_DORM_MESS,
    	//保洁
    	OUT_CLEAN,
    	//物业报修
    	OUT_PROPERTY,
    	//办公用品
    	OUT_DAILYPRODUCT,
    	//物料
    	OUT_MATERIALS,
    	//宿舍报修
    	OUT_DORMITORY,
    	//二维码信息导出
    	OUT_QC_CODE,
		//设备维修及耗材领用
		OUT_CONSUMABLES,
    	//座位与电话申请导出
    	OUT_SEATPHONE,
		OUT_APPROVE_ROLE,
    	//导出供应商信息
		OUT_SUPPLIER_INFO,
		//导出网点数据
		OUT_DOT_DATA,
		//电费管理入账单导出
		OUT_ELECRTRIC_ACCOUNT,
		//水费管理入账单导出
		OUT_WATER_ACCOUNT,
		//管理费入账单导出
		OUT_PROPERTY_ACCOUNT,
		//周专房
		OUT_OTHER_ACCOUNT,
		//管理费公摊导出
		OUT_PROPERTY_SHARE_ACCOUNT,
		OUT_OTHER_SHARE_ACCOUNT,
		//水费公摊导出
		OUT_WATER_SHARE_ACCOUNT,
		//电费公摊导出
		OUT_ELECRTRIC_SHARE_ACCOUNT,

		//奖励兑换导出 20200305 YLK
		OUT_APPREAISE_AWARD,

		//导出自助银行数据
		OUT_DOT_SELF_HELP_DATA;
		public static SsExportEnumType toType(String str) {
			try {
				return valueOf(str);
			} catch (Exception ex) {
				return OUT_LEASE_ACCOUNT;
			}
		}
	}
}
