
package com.gantang.common.sysimport;

import com.gantang.common.excel.CreateCsv;
import com.gantang.common.excel.CreateExcel;
import com.gantang.common.excel.ExcelFieldModel;
import com.gantang.common.excel.ExcelFormat;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.List;
import java.util.Map;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):ExportReportThread
 * @Title(标题):  ExportReportThread.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月26日 下午11:33:28   
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
public class ExportReportThread extends Thread {
	@Value("${web.upload-path}")
	private String rootPath;
	Long fileId;// ss_file表的主键
	String serializeFileName;// 序列化的文件名称
	String reportType;// 报表类型 excel 或者csv
	List<ExcelFieldModel> excelFieldModel;// 生成报表的模板
	String userReportPath;// 用户存储报表的路径
	String actionUrl;// 执行的url
	String userCode;// 用户账号
	Long filterId;// 过滤方案，如果有代表是通过过滤方案生成的
	String reportName;// 报表名称,用于显示在报表中心中作为报表的名称
	List<Map<String, Object>> list;//结果集

	@Override
    public void run() {

		ExcelFormat excel = null;
		if (ExcelFormat.REPORT_TYPE_EXCEL.equals(reportType)) {
			excel = new CreateExcel();
		} else {
			excel = new CreateCsv();
		}
		FileImportUtil.newFolder(rootPath+MvcConfig.REPORT_PATH_IMPORT_REPORT);// create default Floder
		FileImportUtil.newFolder(userReportPath);// create Folder
		excel.setFilePath(userReportPath);
		excel.setResult(list);
		excel.setFieldModel(excelFieldModel);
		String filePath = excel.create();
		File file = new File(filePath);
		String filePathEnd = filePath.substring((rootPath+MvcConfig.REPORT_PATH_IMPORT_REPORT).length());
		// 更新修改状态为报表生成成功
		String fileType = "";
		String fileName = file.getName();
		fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
//		if (filePath.lastIndexOf(".") >= 0 && filePath.length() > 0) {
//			fileType = filePath.substring(filePath.lastIndexOf("."), filePath.length());
//			if(filePath.lastIndexOf(File.separatorChar)>0) {
//				fileName = filePath.substring(filePath.lastIndexOf(File.separatorChar)+1, filePath.length());
//			}else {
//				fileName =filePath; 
//			}
//			
//		}
		
		

		

	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getSerializeFileName() {
		return serializeFileName;
	}

	public void setSerializeFileName(String serializeFileName) {
		this.serializeFileName = serializeFileName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<ExcelFieldModel> getExcelFieldModel() {
		return excelFieldModel;
	}

	public void setExcelFieldModel(List<ExcelFieldModel> excelFieldModel) {
		this.excelFieldModel = excelFieldModel;
	}

	public String getUserReportPath() {
		return userReportPath;
	}

	public void setUserReportPath(String userReportPath) {
		this.userReportPath = userReportPath;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getFilterId() {
		return filterId;
	}

	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


}
