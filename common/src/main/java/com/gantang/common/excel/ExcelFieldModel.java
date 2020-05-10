package com.gantang.common.excel;


/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):oms-common
 * @Package(包名称) com.eclp.excel  
 * @ClassName(类名称):ExcelFieldModel
 * @Title(标题):  ExcelFieldModel.java   
 * @see(与该类相关联的类):  
 * @author(作者): 深圳汇合投资发展有限公司    sl.qiu 
 * @since: JDK1.8
 * @date(创建日期):   2017年11月3日 上午9:59:48   
 * @version(版本): V1.0 
 * @Copyright(版权): 2017 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):   
 * TODO 读取Excel 或 写入CSV 文件的数据模板对象 
 * 注意：本内容仅限于深圳汇合投资发展有限公司有限公司内部传阅，禁止外泄以及用于其他的商业项目
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
public class ExcelFieldModel {
	
	private int width = 40; //列宽度，默认为40
	private String title;// 标题，用于写入表格标题
	private String filed;// 字段，用于读写的取值KEY
	private String format;// 格式，用于读写的值的转换格式
	private int column;// 列 ，用于读写的列下标，从0开始算
	private int row;// 行 ，用于读写的行下标，从0开始算
	
	
	private Object cellValue;//  数据

	private boolean notEmpty = false;// 是否允许空 ，读取时进行校验

	public ExcelFieldModel(String filed, int column) {
		this.filed = filed;
		this.column = column;
	}
	public ExcelFieldModel(String filed, int column,int row) {
		this.filed = filed;
		this.column = column;
		this.row = row;
	}
	public ExcelFieldModel(String filed, int column,int row,String format) {
		this.filed = filed;
		this.column = column;
		this.row = row;
		this.format = format;
	}
	public ExcelFieldModel(int column,int row,Object cellValue ) {
		this.cellValue = cellValue;
		this.column = column;
		this.row = row;
	}
	public ExcelFieldModel(int column,int row,Object cellValue,String format ) {
		this.cellValue = cellValue;
		this.column = column;
		this.row = row;
		this.format = format;
	}

	public ExcelFieldModel(String filed, int column, String format) {
		this.filed = filed;
		this.column = column;
		this.format = format;
	}
	
	public ExcelFieldModel(String filed, String title, String format) {
		this.filed = filed;
		this.title = title;
		this.format = format;
	}

	public ExcelFieldModel(String filed, int column, String format,
			boolean notEmpty) {
		this.filed = filed;
		this.column = column;
		this.format = format;
		this.notEmpty = notEmpty;
	}

	public ExcelFieldModel(String filed,  String title, String format,
			boolean notEmpty) {
		this.filed = filed;
		this.title = title;
		this.format = format;
		this.notEmpty = notEmpty;
	}
	public ExcelFieldModel(int column, String filed, String title) {
		this.filed = filed;
		this.column = column;
		this.title = title;
	}

	public ExcelFieldModel(int column, String filed, String title, String format) {
		this.filed = filed;
		this.column = column;
		this.title = title;
		this.format = format;
	}

	/*public ExcelFieldModel(int column, String filed, String title, WritableCellFormat wcellFormat) {
		this.filed = filed;
		this.column = column;
		this.title = title;
		this.wcellFormat = wcellFormat;
	}
	public ExcelFieldModel( String filed, int column,WritableCellFormat wcellFormat) {
		this.filed = filed;
		this.column = column;

		this.wcellFormat = wcellFormat;
	}*/
	public ExcelFieldModel(int column, String filed, String title, int width,
			String format) {
		this.filed = filed;
		this.column = column;
		this.title = title;
		this.format = format;
		this.width = width;
	}

	public ExcelFieldModel(int column, String filed, String title, int width) {
		this.filed = filed;
		this.column = column;
		this.title = title;
		this.width = width;
	}
	
	
	public boolean isNotBlank() {
		return notEmpty;
	}

	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Object getCellValue() {
		return cellValue;
	}

	public void setCellValue(Object cellValue) {
		this.cellValue = cellValue;
	}

}
