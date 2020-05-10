package com.gantang.common.excel;

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.eccel
 * @ClassName(类名称):ReadExcel
 * @Title(标题):  ReadExcel.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午5:26:28   
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
public  abstract  class ReadExcel {


	public abstract void  setModelTable(List<ExcelFieldModel> modelTable);
	public abstract List<ExcelFieldModel>  getModelTable();
	public abstract void setModelCell(List<ExcelFieldModel> modelCell);
	public abstract List<Map<String,String>> getResultTable();
	public  abstract Map<String,String> getResultMap();
	public abstract Map<Integer, String> getTitleMap();
	public final static String DATA_TYPE_CELL ="CELL";  //读取单元格
	public final static String DATA_TYPE_TITLE ="TITLE"; //读取标题
	public final static String DATA_TYPE_TABLE ="TABLE"; //读取表格数据
	public final static String DATA_TYPE_ALL ="ALL";  //全部读取
	public abstract String getPath();

	public abstract void setPath(String path);
	public abstract void read();
	public abstract void read(String dataType);

	public abstract InputStream getIs();

	public abstract void setIs(InputStream is);

	public abstract int getStartRow();

	public abstract void setStartRow(int startRow);

	public abstract int getTitleRow();

	public abstract void setTitleRow(int titleRow);

	public abstract int getSheetIndex();

	public abstract void setSheetIndex(int sheetIndex);

	public abstract String getSheetName();

	public abstract void setSheetName(String sheetName);
    /**
     * 
     * @Title: getInstance   
     * @Description: TODO 抽象方法，用于自动根据文件名称，判断使用的07的读取方法还是10版本的读取方法
     * @param fileName   
     * @return: ReadExcel      
     * @throws
     * @author: 深圳汇合投资发展有限公司 sl.qiu
     */
	public static  ReadExcel getInstance(String fileName){
		if(StringUtils.isBlank(fileName)){
			throw new RuntimeException("Excel 文件名错误,没有文件名");
		}
		fileName = fileName.toLowerCase();
		if(fileName.endsWith(".xls")){
			return new ReadExcelJxl();
		}else if(fileName.endsWith(".xlsx")){
			return new ReadExcelPoi();
		}else{
			throw new RuntimeException("Excel 文件名错误,没有后缀名");
		}
	}
	
}
