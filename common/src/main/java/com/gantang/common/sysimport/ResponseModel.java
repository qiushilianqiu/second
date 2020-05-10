/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):ResponseModel
 * @Title(标题):  ResponseModel.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午10:53:32   
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
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.model.sysimport
 * @ClassName(类名称):ResponseModel
 * @Title(标题):  ResponseModel.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午10:53:32   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):   生成错误报表
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

public class ResponseModel {
	private String msg; //消息提示
	private boolean success;//是否成功
	
	private String id;//新增加或修改的ID
	
	private String url;//下载文件的URL
	private String fileName;//下载时显示的文件名称
	private String filePath;//下载文件的绝对路径
	private String filePathEnd;//下载文件的绝对路径
    private  Map<String,Object> ids;

	private List<Map<String,Object>> list;



	private Map<String, List<String>> validate;//验证消息
	
	
	private Integer start = 0;
	private Integer limit = 0;
	private Integer pageIndex =1;
	private Integer totalProperty =20;
	 @SuppressWarnings("unchecked")
	private List result;//表格数据
	
	private int updateRow=0;
	public ResponseModel() {

	}

	public ResponseModel(boolean success) {
		this.success = success;
	}

	public ResponseModel(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
	public ResponseModel(boolean success,int updateRow, String msg) {
		this.success = success;
		this.msg = msg;
		this.updateRow = updateRow;
	}
	public ResponseModel(int updateRow) {
		this.success = updateRow>0;
		this.updateRow = updateRow;
	}
	public ResponseModel(int updateRow, String msg) {
		this.success = updateRow>0;
		this.msg = msg;
		this.updateRow = updateRow;
	}

	public ResponseModel(boolean success, String msg, String url) {
		this.success = success;
		this.msg = msg;
		this.url = url;
	}

	public ResponseModel( String fileName, String filePath,boolean success) {
		this.success = success;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePathEnd() {
		return filePathEnd;
	}

	public void setFilePathEnd(String filePathEnd) {
		this.filePathEnd = filePathEnd;
	}

	public Map<String, Object> getIds() {
		return ids;
	}

	public void setIds(Map<String, Object> ids) {
		this.ids = ids;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public Map<String, List<String>> getValidate() {
		return validate;
	}

	public void setValidate(Map<String, List<String>> validate) {
		this.validate = validate;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(Integer totalProperty) {
		this.totalProperty = totalProperty;
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public int getUpdateRow() {
		return updateRow;
	}

	public void setUpdateRow(int updateRow) {
		this.updateRow = updateRow;
	}
	
}
