package com.gantang.common.excel;


import com.gantang.common.util.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import com.gantang.common.util.DateUtil;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.eccel
 * @ClassName(类名称):ReadExcelPoi
 * @Title(标题):  ReadExcelPoi.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午5:25:50   
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
public class ReadExcelPoi extends ReadExcel {

	private String path;// 读取文件的路径
	private InputStream is;// 读取文件的文件流
	private List<ExcelFieldModel> modelTable;// 行读取的数据模板
	private List<ExcelFieldModel> modelCell;// 单元格读取的数据模板
	private List<Map<String, String>> resultTable = new ArrayList<Map<String, String>>();
	private Map<String, String> resultMap = new HashMap<String, String>();
	private Map<Integer, String> titleMap = new HashMap<Integer, String>(); // 标题

	private int startRow = 0; // 读取的起始行，从0开始计数，默认为0
	private int titleRow = 0; // 读取标题所在的行，从0开始计数，默认为0
	private int sheetIndex = 0;// 读取表格的下标，从0开始计数，默认为0
	private String sheetName;// 读取表格的名称
	private int columnsCount = -1;// 整个Excel文件的总列数，初始为-1
	private int rowsCount = -1;// 整个Excel文件的总条数，初始为-1

    public static void main(String[] arges) {
        try {
            ReadExcelPoi r = new ReadExcelPoi();
            // r.setSheetName("Sheet1");
            r.setPath("D://123.xlsx");

            List<ExcelFieldModel> list = new ArrayList<ExcelFieldModel>();
            list.add(new ExcelFieldModel("STORER_CODE", 1, ExcelFormat.NOT_NULL));
            list.add(new ExcelFieldModel("SKU_CODE", 2, ExcelFormat.NOT_NULL));
            list.add(new ExcelFieldModel("SKU_ALIAS", 3));
            list.add(new ExcelFieldModel("NEW_OLD_FlAG", 4));// 新旧货(N/R)

            r.setModelTable(list);
            List<Map<String, String>> result = r.getResultTable();
            if (result != null) {

                Iterator<Map<String, String>> iter = result.iterator();
                while (iter.hasNext()) {
                    Map<String, String> map = iter.next();
                    System.out.println(map.get("STORER_CODE") + "--" + map.get("SKU_CODE") + "--" + map.get("SKU_ALIAS")
                            + "--" + map.get("NEW_OLD_FlAG"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	@Override
    public void read() {
		read(ReadExcel.DATA_TYPE_ALL);// 全部读取
	}

	/**
	 * 读取Excel数据的方法
     * <p>Title: read</p>
     * <p>Description: </p>
     * @param dataType
	 */
	@Override
    public void read(String dataType) {
		if (null == is) {
			return;
		}
		Workbook wb = null;
		Sheet st = null;
		try {
			wb = WorkbookFactory.create(is);
			if (null == wb) {
				return;
			}
			st = wb.getSheetAt(this.sheetIndex);

			if (st == null) {
				throw new RuntimeException("can't not find sheet index of '" + this.sheetIndex + "'!");
			}
			if (StringUtils.isNotBlank(this.sheetName)) {
				st = wb.getSheet(this.sheetName);
				if (st == null) {
					throw new RuntimeException("can't not find sheet name of '" + this.sheetName + "'!");
				}
			}
			this.sheetName = st.getSheetName();
			int rows = st.getPhysicalNumberOfRows();
			this.setRowsCount(rows);

			Row hssfRow = st.getRow(0); // 取得总列数

			int columnNum = hssfRow.getPhysicalNumberOfCells() > hssfRow.getLastCellNum()
					? hssfRow.getPhysicalNumberOfCells()
					: hssfRow.getLastCellNum();
			this.setColumnsCount(columnNum);
			if (ReadExcel.DATA_TYPE_CELL.equals(dataType) || ReadExcel.DATA_TYPE_ALL.equals(dataType)) {
				readResultMap(st);
			}
			if (ReadExcel.DATA_TYPE_TITLE.equals(dataType) || ReadExcel.DATA_TYPE_ALL.equals(dataType)) {
				readTitleMap(st);
			}
			if (ReadExcel.DATA_TYPE_TABLE.equals(dataType) || ReadExcel.DATA_TYPE_ALL.equals(dataType)) {
				readResultTable(st, rows);
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (null != is) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// isread = true;
	}

	/**
     * @Title: readTitleMap
     * @Description: TODO 读取Excel的标题数据
     * @param st 表格对象
     * @return: void
     * @throws
	 * @author:  sl.qiu
	 */
	private void readTitleMap(Sheet st) {
		if (this.getRowsCount() <= 0) {
			return;
		}
		int columns = this.getColumnsCount();
		if (columns <= 0) {
			return;
		}
		for (int i = 0; i < columns; i++) {
			ExcelFieldModel fieldModel = new ExcelFieldModel("TITLE_" + i, i, getTitleRow());
			String cellValue = readRow(st, fieldModel);
			titleMap.put(i, cellValue);
		}
	}

	/**
     * @Title: readResultMap
     * @Description:
     * TODO 根据单元格数据模板，读取表格单元格数据
     * @param st
     * @return: void
     * @throws
	 * @author: 深圳汇合投资发展有限公司 sl.qiu
	 */
	private void readResultMap(Sheet st) {
		if (this.modelCell == null) {
			return;
		}
		Iterator<ExcelFieldModel> iter = modelCell.iterator();
		while (iter.hasNext()) {
			ExcelFieldModel fieldModel = iter.next();
			if (null == fieldModel) {
				continue;
			}
			if (null == fieldModel.getFiled()) {
				continue;
			}
			String cellvolue = readRow(st, fieldModel);
			resultMap.put(fieldModel.getFiled(), cellvolue);
		}
	}

	/**
     *
     * @Title: readResultTable
     * @Description:  TODO 根据表格数据模板，读取表格所有行数据
	 * @param st 表格对象
     * @param rows  行
     * @return: void
     * @throws
	 * @author:  sl.qiu
	 */
	private void readResultTable(Sheet st, int rows) {

		if (null == this.getModelTable()) {
			return;
		}
		if (this.startRow == 0) {
			this.startRow = this.startRow + 1;
		}
		if (this.startRow == -1) {
			this.startRow = this.startRow + 1;
		}

		for (int i = this.startRow; i < rows; i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (ExcelFieldModel fieldModel : modelTable) {
				if (null == fieldModel)
					continue;
				if (StringUtils.isBlank(fieldModel.getFiled()))
					continue;
				fieldModel.setRow(i);
				String cellVolue = readRow(st, fieldModel);
				fieldModel.setNotEmpty(ExcelFormat.NOT_NULL.equals(fieldModel.getFormat()));

				if (StringUtils.isBlank(cellVolue) && fieldModel.isNotBlank()) {
					map.clear();
					break;
				}

				map.put(fieldModel.getFiled(), cellVolue.trim());
			}
			if (CollectionUtil.isNullMap(map)) {
				continue;
			}
			this.resultTable.add(map);
		}
	}

	@Override
    public String getPath() {
		return path;
	}

	@Override
    public void setPath(String path) {
		this.path = path;
		try {
			this.is = new FileInputStream(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
    public InputStream getIs() {
		return is;
	}

	@Override
    public void setIs(InputStream is) {
		this.is = is;
	}

	@Override
    public int getStartRow() {
		return startRow;
	}

	@Override
    public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	@Override
    public int getTitleRow() {
		return titleRow;
	}

	@Override
    public void setTitleRow(int titleRow) {
		this.titleRow = titleRow;
	}

	@Override
    public int getSheetIndex() {
		return sheetIndex;
	}

	@Override
    public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	@Override
    public List<Map<String, String>> getResultTable() {

		read(ReadExcel.DATA_TYPE_TABLE);
		return resultTable;

	}

	@Override
    public Map<String, String> getResultMap() {

		read(ReadExcel.DATA_TYPE_CELL);
		return resultMap;

	}

	@Override
    public Map<Integer, String> getTitleMap() {
		read(ReadExcel.DATA_TYPE_TITLE);
		return titleMap;
	}

    public void setTitleMap(Map<Integer, String> titleMap) {
        this.titleMap = titleMap;
    }

	@Override
    public List<ExcelFieldModel> getModelTable() {
		return this.modelTable;
	}

    @Override
    public void setModelTable(List<ExcelFieldModel> modelTable) {
        this.modelTable = modelTable;
    }

	@Override
    public void setModelCell(List<ExcelFieldModel> modelCell) {
		this.modelCell = modelCell;
	}

	@Override
    public String getSheetName() {
		return sheetName;
	}

	@Override
    public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public boolean isOutOfArray(Sheet st, int x, int y) {
        return x > this.getColumnsCount() || y > st.getPhysicalNumberOfRows();
    }

	public String readRow(Sheet st, int x, int y) {
		return isOutOfArray(st, x, y) ? "" : cell(st.getRow(y).getCell(x), null);
	}

	public String readRow(Sheet st, int x, int y, String format) {
		return isOutOfArray(st, x, y) ? "" : cell(st.getRow(y).getCell(x), format);
	}

	public String readRow(Sheet st, ExcelFieldModel fieldModel) {

		if (isOutOfArray(st, fieldModel.getColumn(), fieldModel.getRow())) {
			return "";
		} else {
			Row row = st.getRow(fieldModel.getRow());
			if (row != null) {

				Cell cell = row.getCell(fieldModel.getColumn());

				return cell(cell, fieldModel.getFormat());
			} else {
				return "";
			}
		}

		// return isOutOfArray(st, fieldModel.getColumn(), fieldModel.getRow()) ? "" :
		// cell(st.getRow(fieldModel.getRow()).getCell(fieldModel.getColumn()),fieldModel.getFormat());
	}

	public String cell(Cell cell, String format) {
		String cellvolue = "";
		if (null == cell) {
			return cellvolue;
		}
		DecimalFormat df;
		SimpleDateFormat sdf;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数值型
			if (StringUtils.isNotBlank(format)) {
				if (format.indexOf("#") >= 0) {
					df = new DecimalFormat(format);
					cellvolue = df.format(cell.getNumericCellValue());
				} else if (format.toUpperCase().indexOf("Y") >= 0 || format.toUpperCase().indexOf("H") >= 0) {
					sdf = new SimpleDateFormat(format);
					cellvolue = sdf.format(cell.getDateCellValue());
				} else {
					df = new DecimalFormat("#");
					cellvolue = df.format(cell.getNumericCellValue());
				}
			} else {
				df = new DecimalFormat("#");
				cellvolue = df.format(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_STRING: // 字符串型
			if (StringUtils.isNotBlank(format)) {
				if (format.toUpperCase().indexOf("Y") >= 0 || format.toUpperCase().indexOf("H") >= 0) {
					if (cell.getRichStringCellValue().getString().equals("")) {
						cellvolue = "";
					} else {

						cellvolue = cell.getRichStringCellValue().getString();
						try {
							String date = DateUtil.getDateLong(cellvolue);
							cellvolue = DateUtil.format(date, format);
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException(e.getMessage());
						}
						/*
						 * String value = cell.getRichStringCellValue().getString();
						 * if(value.indexOf("/")>=0){ value = value.replace('/', '-'); } sdf = new
						 * SimpleDateFormat(format); try { cellvolue = sdf.format(sdf.parse(value)); }
						 * catch (ParseException e) { e.printStackTrace(); throw new
						 * EricRuntimeException(e.getMessage()); }
						 */
					}
				} else {
					cellvolue = cell.getRichStringCellValue().getString();
				}
			} else {
				cellvolue = cell.getRichStringCellValue().getString();
			}
			break;

		case Cell.CELL_TYPE_FORMULA:// 公式型
			cellvolue = String.valueOf(cell.getNumericCellValue());
			if (cellvolue.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
				cellvolue = cell.getRichStringCellValue().toString();
			}
			break;

		case Cell.CELL_TYPE_BOOLEAN:// 布尔
			cellvolue = cell.getBooleanCellValue() ? "true" : "false";
			break;

		case Cell.CELL_TYPE_BLANK: // 空值
			cellvolue = "";
			break;

		case Cell.CELL_TYPE_ERROR: // 故障
			cellvolue = "";
			break;

		default:
			cellvolue = cell.getRichStringCellValue().toString();

		}
		return cellvolue;
	}

	public int getColumnsCount() {

		return columnsCount;
	}

	public void setColumnsCount(int columnsCount) {
		this.columnsCount = columnsCount;
	}

	public int getRowsCount() {

		return rowsCount;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
}
