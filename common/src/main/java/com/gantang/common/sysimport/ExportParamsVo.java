/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):asfas
 * @Title(标题):  asfas.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月25日 下午3:32:17   
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

import java.util.List;
import java.util.Map;

/**
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * 
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):asfas @Title(标题): asfas.java @see(与该类相关联的类): @author(作者):
 *                       sl.qiu
 * @since: JDK1.8 @date(创建日期): 2018年7月25日 下午3:32:17 @version(版本):
 *         V1.0 @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights
 *         reserved. @Description(描述): TODO(这里描述这个文件做什么 – 可选)
 *         注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目 ==== All rights Reserved,
 *         Designed By www.gantang.com.cn ====
 *         —————————————————————————————————————————————————————————————————
 *         修改记录 修改者： 修改时间： 复审人: 修改原因：
 * 
 *         —————————————————————————————————————————————————————————————————
 */
public class ExportParamsVo implements java.io.Serializable {
//	private static final long serialVersionUID = 1L;
	/************************************************************ 公有参数 ************************************************************/
	private Long importConfigId;// 导入模板ID
	private Long exportConfigId;// 导出模板ID
	private String moduleCode;// 模板编码
	private String reportName;// 下载模板名称// 报表名称,用于显示在报表中心中作为报表的名称
	private List<Map<String, Object>> exportFormatList;// 返回的模板表头
	private List<Map<String, Object>> list;// 结果集
	/************************************************************ 自定义参数，可根据业务自己定义 ************************************************************/
	private String heTongId;// 合同Id
	private String contractNumber;// 合同编码
	private String dotId;// 网点编码
	private String periodPayment;// 付款月份
	private String starttime;// 付款月份
	private String endtime;// 付款月份
	private String zoneId;// 宿舍片区ID
	private String nameOrganization;// 付款月份
	private String content;// 缴费类型
	private String costCenter;// 成本中心
	private String failureSign;// 成本中心
	private int areachoice;// 宿舍片区
	private String keyword;// 关键字
	private String departnameId;//组织架构Id
	private String mobilephone;//手机号码
	private String type;//员工类型
	private String leaseName;//合同名称
	private String leaseType;//合同类型
	private String cleanType;//保洁类型
	private String network;//网点/部室
	private int areaId;
	private String applyType;//申请类型
	private String applyPersonName;//申请人
	private String applyUser;//申请人
	private String repairTypeSup;//报修类型

	private String name;//供应商名字
	private String chargeName;//负责人名字
	private String contact;//联系方式

	private String searchVague;//模糊查询
	private String initFlag;//录用标志

	private String dotName;//网点名称
	private int dot_type;//网点类型
	private char delFlag;//网点状态
	private String fromDecorationYears;//起始装修年限
	private String decorationYears;//装修年限

	private String applyStatus;	//维修状态
	private String startTime;	//开始日期
	private String endTime;	//结束日期
	private String status;
	private String ids;//维修状态
	private String modelFlag;
	private String propertyNames;

	public String getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(String propertyNames) {
		this.propertyNames = propertyNames;
	}

	public String getModelFlag() {
		return modelFlag;
	}

	public void setModelFlag(String modelFlag) {
		this.modelFlag = modelFlag;
	}

	/**
	 * 设备名称
	 */
	private String equipAddress;
	/**
	 * 行政区
	 */
	private String district;
	/**
	 * 网点自助状态
	 */
	private Integer flag;

	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSearchVague() {
		return searchVague;
	}

	public void setSearchVague(String searchVague) {
		this.searchVague = searchVague;
	}

	public String getInitFlag() {
		return initFlag;
	}

	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}

	public String getCleanType() {
		return cleanType;
	}

	public void setCleanType(String cleanType) {
		this.cleanType = cleanType;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getRepairTypeSup() {
		return repairTypeSup;
	}

	public void setRepairTypeSup(String repairTypeSup) {
		this.repairTypeSup = repairTypeSup;
	}

	public String getApplyPersonName() {
		return applyPersonName;
	}

	public void setApplyPersonName(String applyPersonName) {
		this.applyPersonName = applyPersonName;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public String getLeaseName() {
		return leaseName;
	}

	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getAreaId() {
		return areaId;
	}

	public String getDepartnameId() {
		return departnameId;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public void setDepartnameId(String departnameId) {
		this.departnameId = departnameId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getAreachoice() {
		return areachoice;
	}

	public void setAreachoice(int areachoice) {
		this.areachoice = areachoice;
	}

	public String getFailureSign() {
		return failureSign;
	}

	public void setFailureSign(String failureSign) {
		this.failureSign = failureSign;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNameOrganization() {
		return nameOrganization;
	}

	public void setNameOrganization(String nameOrganization) {
		this.nameOrganization = nameOrganization;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public List<Map<String, Object>> getExportFormatList() {
		return exportFormatList;
	}

	public void setExportFormatList(List<Map<String, Object>> exportFormatList) {
		this.exportFormatList = exportFormatList;
	}

	public Long getExportConfigId() {
		return exportConfigId;
	}

	public void setExportConfigId(Long exportConfigId) {
		this.exportConfigId = exportConfigId;
	}

	public String getPeriodPayment() {
		return periodPayment;
	}

	public void setPeriodPayment(String periodPayment) {
		this.periodPayment = periodPayment;
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

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	public String getHeTongId() {
		return heTongId;
	}

	public void setHeTongId(String heTongId) {
		this.heTongId = heTongId;
	}

	public String getDotId() {
		return dotId;
	}

	public void setDotId(String dotId) {
		this.dotId = dotId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDotName() {
		return dotName;
	}

	public void setDotName(String dotName) {
		this.dotName = dotName;
	}

	public int getDot_type() {
		return dot_type;
	}

	public void setDot_type(int dot_type) {
		this.dot_type = dot_type;
	}

	public char getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(char delFlag) {
		this.delFlag = delFlag;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEquipAddress() {
		return equipAddress;
	}

	public void setEquipAddress(String equipAddress) {
		this.equipAddress = equipAddress;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDecorationYears() {
		return decorationYears;
	}

	public void setDecorationYears(String decorationYears) {
		this.decorationYears = decorationYears;
	}

	public String getFromDecorationYears() {
		return fromDecorationYears;
	}

	public void setFromDecorationYears(String fromDecorationYears) {
		this.fromDecorationYears = fromDecorationYears;
	}

	/*
	 * private String reportType ;//报表类型 Excel 或者CSV private String
	 * userReportPath;//用户的报表保存路径 private String actionUrl;//执行的ActionUrl
	 * private String userCode;//操作用户 private String
	 * serializeFileName;//序列化查询对象的文件名字 private Long fileId;//保存的文件ID private
	 * String exportFormat;//导出报表的格式
	 * 
	 * private Long filterId; //过滤方案ID
	 * 
	 * private String reportName;//报表名称
	 * 
	 * 
	 * public Long getFilterId() { return filterId; } public void
	 * setFilterId(Long filterId) { this.filterId = filterId; } public String
	 * getReportType() { return reportType; } public void setReportType(String
	 * reportType) { this.reportType = reportType; } public String
	 * getUserReportPath() { return userReportPath; } public void
	 * setUserReportPath(String userReportPath) { this.userReportPath =
	 * userReportPath; } public String getActionUrl() { return actionUrl; }
	 * public void setActionUrl(String actionUrl) { this.actionUrl = actionUrl;
	 * } public String getUserCode() { return userCode; } public void
	 * setUserCode(String userCode) { this.userCode = userCode; } public String
	 * getSerializeFileName() { return serializeFileName; } public void
	 * setSerializeFileName(String serializeFileName) { this.serializeFileName =
	 * serializeFileName; } public Long getFileId() { return fileId; } public
	 * void setFileId(Long fileId) { this.fileId = fileId; } public String
	 * getExportFormat() { return exportFormat; } public void
	 * setExportFormat(String exportFormat) { this.exportFormat = exportFormat;
	 * } public String getReportName() { return reportName; } public void
	 * setReportName(String reportName) { this.reportName = reportName; }
	 */

}
