/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util
 * @ClassName(类名称):aaa
 * @Title(标题):  aaa.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午8:46:53   
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


import com.gantang.common.user.User;

/**
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util
 * @ClassName(类名称):aaa
 * @Title(标题):  aaa.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午8:46:53   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  上传工具类 
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
public class UploadVo {
		/************************************************************公有参数************************************************************/
    	private Long importConfigId;//导入模板ID
    	private String moduleCode;//模板编码
    	private String reportName;//下载模板名称
    	private User user;//用户信息
    	/************************************************************自定义参数，可根据业务自己定义************************************************************/
    	private int heTongId;//合同Id
    	private String contractNumber;//合同编码
    	private int dotId;//网点编码
    	private String token;//token
    	
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public Long getImportConfigId() {
			return importConfigId;
		}
		public void setImportConfigId(Long importConfigId) {
			this.importConfigId = importConfigId;
		}
		public String getModuleCode() {
			return moduleCode;
		}
		public void setModuleCode(String moduleCode) {
			this.moduleCode = moduleCode;
		}
		public String getReportName() {
			return reportName;
		}
		public void setReportName(String reportName) {
			this.reportName = reportName;
		}
		public int getHeTongId() {
			return heTongId;
		}
		public void setHeTongId(int heTongId) {
			this.heTongId = heTongId;
		}
		public String getContractNumber() {
			return contractNumber;
		}
		public void setContractNumber(String contractNumber) {
			this.contractNumber = contractNumber;
		}
		public int getDotId() {
			return dotId;
		}
		public void setDotId(int dotId) {
			this.dotId = dotId;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
    	
    	
}
