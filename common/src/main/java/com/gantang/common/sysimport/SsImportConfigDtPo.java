
package com.gantang.common.sysimport;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):aaa
 * @Title(标题):  aaa.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午9:26:15   
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
@SuppressWarnings("serial")
@Setter
@Getter
public class SsImportConfigDtPo implements Serializable {
	private Long id;// 流水号
	private Long importConfigId;// 报表ID
	private String excelTitle;// Excel标题
	private Long colIndex;// 第几列
	private String useStatus;// 使用状态
	private String createBy;// 创建人
	private String createDate;// 创建时间
	private String modifyBy;// 修改人
	private String modifyDate;// 修改时间
	private Long importDtId;// 基础明细ID
	private String allowBlank;// 允许为空
	private String remark;// 备注说明

	private String actionMethod;// 执行的方法名
	private String fieldNcn;// 字段说明
	private Long lineSeq;// 排序

	private Long fieldLength;// 字段长度
	private String fieldDateType;// 字段类型
	private String fieldDateFormat;// 字段格式

	private String fieldName;// 字段名称

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImportConfigId() {
		return importConfigId;
	}

	public void setImportConfigId(Long importConfigId) {
		this.importConfigId = importConfigId;
	}

	public String getExcelTitle() {
		return excelTitle;
	}

	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}

	public Long getColIndex() {
		return colIndex;
	}

	public void setColIndex(Long colIndex) {
		this.colIndex = colIndex;
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

	public Long getImportDtId() {
		return importDtId;
	}

	public void setImportDtId(Long importDtId) {
		this.importDtId = importDtId;
	}

	public String getAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(String allowBlank) {
		this.allowBlank = allowBlank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getFieldNcn() {
		return fieldNcn;
	}

	public void setFieldNcn(String fieldNcn) {
		this.fieldNcn = fieldNcn;
	}

	public Long getLineSeq() {
		return lineSeq;
	}

	public void setLineSeq(Long lineSeq) {
		this.lineSeq = lineSeq;
	}

	public Long getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Long fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldDateType() {
		return fieldDateType;
	}

	public void setFieldDateType(String fieldDateType) {
		this.fieldDateType = fieldDateType;
	}

	public String getFieldDateFormat() {
		return fieldDateFormat;
	}

	public void setFieldDateFormat(String fieldDateFormat) {
		this.fieldDateFormat = fieldDateFormat;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	
}
