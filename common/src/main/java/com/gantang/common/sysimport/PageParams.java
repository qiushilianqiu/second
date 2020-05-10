/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):ca
 * @Title(标题):  ca.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月25日 下午3:21:53   
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gantang.common.excel.CreateCsv;
import com.gantang.common.excel.CreateExcel;
import com.gantang.common.excel.ExcelFieldModel;
import com.gantang.common.excel.ExcelFormat;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import com.gantang.common.util.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):ca
 * @Title(标题):  ca.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月25日 下午3:21:53   
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
public class PageParams implements java.io.Serializable{

	
	protected Long start; // page strat
	protected Long limit; // page size
	protected  String  summary; //summary filed and summary Type String
	protected Long rowcount = -1l; // total record count,if -1 than
	protected String params; // ext query params
	protected String sort;// 排序字段
	protected String dir;// 排序的规则
	protected String ids;// 页面多选的id用","隔开，如,"1,2,3"
	protected Integer deleteCount;// 页面已经删除条数
	protected String rows;// 页面保存方法的参数,格式为JSON字符串{[],[]}

	private Long filterId;//过滤方案ID

	private String exportFormat; //报表导出格式JSON
	private Map<String, List<String>> message;// 验证的错误信息
	private List<Map<String, Object>> gridData;
	// Export Excel
	protected String exportType;// 导出的类型all,page,

	protected String exportDefinedFlag="Y";// 是否使用系统配置的报表格式  Y使用 N不适用，使用代码格式

    protected String reportType ="EXCEL";// EXCEL or CSV (export to Excel or Csv)     default EXCEL

	
	protected String reportName; //报表的名字

	protected String actionUrl ; //执行的url

	protected String fields ; //导出报表的字段，,格式为JSON字符串{[],[]}



	public Long getFilterId() {
		return filterId;
	}

	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getExportDefinedFlag() {
		return exportDefinedFlag;
	}

	public void setExportDefinedFlag(String exportDefinedFlag) {
		this.exportDefinedFlag = exportDefinedFlag;
	}

	public Integer getDeleteCount() {
		return deleteCount;
	}
	public void setDeleteCount(Integer deleteCount) {
		this.deleteCount = deleteCount;
	}
	/**
	 * @Title: getGridData
	 * @author: sl.qiu
	 * @return List<Map<String,String>>
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getGridData() {
		if (StringUtils.isBlank(this.rows)) {
			gridData = new ArrayList<Map<String, Object>>();
			return gridData;
		}
		gridData = JsonUtil.json2Collection(this.rows);

		return gridData;

	}
	/**
	 *  get id array
	 * @return
	 */
	public String[] getIdArray() {
		if(StringUtils.isNotBlank(ids)){
			String[] idArray=ids.split(",");
			return idArray;
		}
		return new String[]{};
	}

	

	


 

   
   /**
    *  create excel or csv report
    * @author: sl.qiu
    * @param excelFileModelList
    * @param list
    * @return  file name
    */
  public Result export(List<ExcelFieldModel> excelFileModelList, List<Map<String, Object>> list, String rootPath){
	   final String reportType =  this.getReportType();
	   final List<ExcelFieldModel> eFList =  excelFileModelList;
//	   final String userReportPath = MvcConfig.REPORT_PATH_IMPORT_TEMPLATE;
/*	   final String  actionUrl = this.getActionUrl();
	   final String  userId = SessionUtil.getUserCode();*/
	   final String reportName =  this.reportName;	  
	   ExcelFormat excel =null;
	   if(ExcelFormat.REPORT_TYPE_EXCEL.equals(reportType)){
		   excel=  new CreateExcel();
	   }else{
		   excel=  new CreateCsv();
	   }
	   FileImportUtil.newFolder(rootPath);
	   excel.setFilePath(rootPath);
	   excel.setFileName(reportName);
	   excel.setResult(list);
	   excel.setFieldModel(eFList);
	   String filePath = excel.create();
	   String filePathEnd = filePath.substring(rootPath.length());
	   //返回报表正在生成中
	   Map<String,String> map=new HashMap<String,String>();
	   map.put("filePathEnd",filePathEnd);
	   map.put("reportName",reportName);
	   map.put("filePath",filePath);
	   return ResultGenerator.genSuccessResult(map);
  }

	public String exportErrorFile(String reportFileName ,List<ExcelFieldModel> excelFileModelList,List list,String rootPath){
		 this.setReportName(reportFileName);
		if(StringUtils.isBlank(getReportName())){
			this.reportName = DateUtil.dispLong3(new Date());
		}

		ExcelFormat excel =null;
		if(ExcelFormat.REPORT_TYPE_EXCEL.equals(this.getReportType())){
			excel=  new CreateExcel();
		}else{
			excel=  new CreateCsv();
		}

		FileImportUtil.newFolder(rootPath);//create default Floder
		FileImportUtil.newFolder(CountUtil.getUserReportPath());//create Folder
		excel.setFilePath(CountUtil.getUserReportPath());
		excel.setResult(list);
		excel.setFieldModel(excelFileModelList);
		String filePath = excel.create();

		String filePathEnd = filePath.substring(rootPath.length());
		
		return ResultImport.json(reportName,  filePathEnd,false);
		
	}
   
 /*  public String export(List<ExcelFieldModel> excelFileModelList,Pagination page){
	   return export( excelFileModelList,page.getList());
   }*/

	public  List<ExcelFieldModel> getExportModel(){
		List<ExcelFieldModel> excelFieldModelList = new ArrayList<ExcelFieldModel>();
		String exportJson = this.getExportFormat();
		if(StringUtils.isNotBlank(exportJson)) {

			String   field = null;
			String   fieldDataType = null;
			String   fieldDataFormat = null;
			String   fieldNcn = null;
			JSONArray jsonArray = JSON.parseArray(exportJson);
			int size =  jsonArray.size();
			int lineSeq = 0;
			for (int i = 0 ; i < size; i++) {
				//"FIELD", "SEQ", "WIDTH", "FIELD_NCN", "FORMAT","DATA_TYPE"
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				int width = StrUtils.null2zero(jsonObject.getString("WIDTH"));
				width = width/3;
				field=  StrUtils.null2empty(jsonObject.getString("FIELD"));
				fieldNcn =  StrUtils.null2empty(jsonObject.getString("FIELD_NCN"));
				fieldDataType =  StrUtils.null2empty(jsonObject.getString("DATA_TYPE"));
				fieldDataFormat =  StrUtils.null2empty(jsonObject.getString("FORMAT"));
				//文本类型
				if("S".equals(fieldDataType)){
					excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width));
				}
				//数字
				else if("I".equals(fieldDataType)){
					if(StringUtils.isNotBlank(fieldDataFormat)){
						excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width,fieldDataFormat));
					}else{

						excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width,"#0"));
					}
				}
				//日期
				else if("D".equals(fieldDataType)){
					if(StringUtils.isNotBlank(fieldDataFormat)){
						excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width,fieldDataFormat));
					}else{

						excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width, ExcelFormat.DATE_LONGER));
					}
				}else{
					if(StringUtils.isNotBlank(fieldDataFormat)){
						excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width,fieldDataFormat));
					}else{

						excelFieldModelList.add(new ExcelFieldModel(lineSeq++,field,fieldNcn,width));
					}

				}
			}

		}
		return  excelFieldModelList;
	}


/*	public String ResultImport(Pagination page){

		if(isExport()){
			    String reportType =  this.getReportType();
			    String userReportPath = CountUtil.getUserReportPath();
			    String actionUrl = this.getActionUrl();
			    String userCode = SessionUtil.getUserCode();
			    String serializeFileName = page.getFinderFileName();
			    String exportFormat = this.getExportFormat();
			    String filterId = this.filterId!=null? this.filterId.toString():"";
			    String reportName = this.reportName;
			    ExportParamsVo exportParamsVo = new ExportParamsVo();
			    exportParamsVo.setActionUrl(actionUrl);
			    exportParamsVo.setExportFormat(this.getExportFormat());
			    exportParamsVo.setReportType(reportType);
			    exportParamsVo.setUserReportPath(userReportPath);
			    exportParamsVo.setUserCode(userCode);
			    exportParamsVo.setSerializeFileName(page.getFinderFileName());
			    exportParamsVo.setReportName(reportName);
			    exportParamsVo.setFilterId(this.filterId);
			    
			    //发送http请求
			    Map<String, String> params = new HashMap<String, String>();
			    params.put("reportType", reportType);
			    params.put("userReportPath", userReportPath);
			    params.put("actionUrl", actionUrl);
			    params.put("userCode", userCode);
			    params.put("serializeFileName", serializeFileName);
			    params.put("exportFormat", exportFormat);
			    params.put("reportName", reportName);
			    params.put("filterId", filterId);
	
			    
			    
			    HashMap<String, String> map=HttpUtils.sendPost("http://localhost:8080/oms/ss/export", params);
			    
			    if("200".equals(F.filed(map, "code"))) {
			     return	ResultImport.json(F.filed(map, "ResultImport"));
			    }else {
			     return	ResultImport.json(F.filed(map, "msg"));
			    }
			    
			  //return export(getExportModel(), page);
		}
		else{
			return ResultImport.json(page);
		}
	}*/
/*   public String ResultImport(List<ExcelFieldModel> excelFileModelList,Pagination page){
	   if(isExport()){
		   return export(excelFileModelList, page);
		}
	   return ResultImport.json(page);
   }*/
/*   public String ResultImport(List<ExcelFieldModel> excelFileModelList,List list){
	   if(isExport()){
		return export(excelFileModelList, list);
		}
	   return ResultImport.json(list);
   }*/
   /**
    * whether  is export report
    * @return   true -> export report false ->  page display
    */
 /* public boolean isExport(){
	 return  getPagination().isExport();
  }*/

	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	public Long getLimit() {
		return limit;
	}
	public void setLimit(Long limit) {
		this.limit = limit;
	}
	public Long getRowcount() {
		return rowcount;
	}
	public void setRowcount(Long rowcount) {
		this.rowcount = rowcount;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getIds() {
		return ids;
	}
	
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	public Map<String, List<String>> getMessage() {
		return message;
	}
	public void setMessage(Map<String, List<String>> message) {
		this.message = message;
	}

	public void setGridData(List<Map<String, Object>> gridData) {
		this.gridData = gridData;
	}
	
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getExportFormat() {
		return exportFormat;
	}

	public void setExportFormat(String exportFormat) {
		this.exportFormat = exportFormat;
	}
}

