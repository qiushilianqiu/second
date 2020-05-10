/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):SsImportConfig
 * @Title(标题):  SsImportConfig.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午10:05:56   
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

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):SsImportConfig
 * @Title(标题):  SsImportConfig.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午10:05:56   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):   
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
//实体
@SuppressWarnings("serial")
@Getter
@Setter
public class SsImportConfig implements Serializable {
	
  private Long id;//流水号
  private String planName;//方案名称
  private String moduleCode;//模块编码
  private String useStatus;//使用状态 默认为Y
  private String createBy;//创建人
  private String createDate;//创建日期
  private String modifyBy;//修改人
  private String modifyDate;//修改日期
  private Long startRow;//开始读取的行  默认为1
  private Long titleRow;//标题行 默认为1
  private String remarkDesc;//备注说明
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getPlanName() {
	return planName;
}
public void setPlanName(String planName) {
	this.planName = planName;
}
public String getModuleCode() {
	return moduleCode;
}
public void setModuleCode(String moduleCode) {
	this.moduleCode = moduleCode;
}
public String getUseStatus() {
	return useStatus;
}
public void setUseStatus(String useStatus) {
	this.useStatus = useStatus;
}
public String getCreateBy() {
	return createBy;
}
public void setCreateBy(String createBy) {
	this.createBy = createBy;
}
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
public String getModifyBy() {
	return modifyBy;
}
public void setModifyBy(String modifyBy) {
	this.modifyBy = modifyBy;
}
public String getModifyDate() {
	return modifyDate;
}
public void setModifyDate(String modifyDate) {
	this.modifyDate = modifyDate;
}
public Long getStartRow() {
	return startRow;
}
public void setStartRow(Long startRow) {
	this.startRow = startRow;
}
public Long getTitleRow() {
	return titleRow;
}
public void setTitleRow(Long titleRow) {
	this.titleRow = titleRow;
}
public String getRemarkDesc() {
	return remarkDesc;
}
public void setRemarkDesc(String remarkDesc) {
	this.remarkDesc = remarkDesc;
}

}
