package com.gantang.common.excel;

import com.gantang.common.util.StrUtils;
import com.gantang.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.eccel
 * @ClassName(类名称):CreateExcel
 * @Title(标题):  CreateExcel.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午5:26:34   
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
public class CreateExcel implements ExcelFormat {
	static Random r = new Random();
    XSSFDataFormat dateFormat;
    private List<ExcelFieldModel> fieldModel; //行格式读取模板对象
	private List<ExcelFieldModel> cellModel;  //单元格格式模板对象
	private List<Map<String,Object>> result;  //要填写的集合数据
	private String filePath; //文件路径
	private String fileName; //文件名称
	private String sheetName; //表格名称
	private boolean newSheet; //是否为新的表格
	private int startRow=0; //写入的起始行，从0开始计数，默认为0
	private int scaleFactor=0; //页面的缩放比例，默认为0
	private  CellStyle cellStyle;//边框样式
	private  CellStyle cellStyleTitle;//边框样式
	private  CellStyle cellStyleInteger;//数字样式
	private  Font font;//字体
	private CreationHelper helper;
	private SXSSFWorkbook wb = null; //工作簿


//	private  CellStyle cellStyleDouble1;//数字样式
//	private  CellStyle cellStyleDouble2;//数字样式
//	private  CellStyle cellStyleDouble3;//数字样式
//	private  CellStyle cellStyleDouble4;//数字样式
	private Map<String,CellStyle>   cellStyleMap;

    public static void main(String[] arge) {
        List<ExcelFieldModel> excelFieldModelList = new ArrayList<ExcelFieldModel>();
        int i = 0;
        excelFieldModelList.add(new ExcelFieldModel(i++, "ID", "ID", 150, "m/d/yyyy"));
        excelFieldModelList.add(new ExcelFieldModel(i++, "BUID", "BUID", 150, "#00.0"));

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int k = 0; k < 30; k++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ID", "20121213160000");
            map.put("BUID", "12.333");
            list.add(map);
        }
        CreateExcel createExcel = new CreateExcel();
        //createExcel.setFilePath("D:\\");
        //createExcel.setResult(list);
        //createExcel.setFieldModel(excelFieldModelList);
        //createExcel.create();
        createExcel.copySheet("D:\\1.xlsx", 0, "3344");

    }

    /**
     * 设置文字在单元格里面的位置
     * CellStyle.ALIGN_CENTER
     * CellStyle.VERTICAL_CENTER
     *
     * @param cellStyle
     * @param halign
     * @param valign
     * @return
     */
    public static CellStyle setCellStyleAlignment(CellStyle cellStyle, short halign, short valign) {
        //设置上下
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        //设置左右
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return cellStyle;
    }

    /**
     * 格式化单元格
     * 如#,##0.00,m/d/yy去HSSFDataFormat或XSSFDataFormat里面找
     *
     * @param cellStyle
     * @param fmt
     * @return
     */
    public static CellStyle setCellFormat(CreationHelper helper, CellStyle cellStyle, String fmt) {
        //还可以用其它方法创建format
        cellStyle.setDataFormat(helper.createDataFormat().getFormat(fmt));
        return cellStyle;
    }

    /**
     * 前景和背景填充的着色
     *
     * @param cellStyle
     * @param bg        IndexedColors.ORANGE.getIndex();
     * @param fg        IndexedColors.ORANGE.getIndex();
     * @param fp        CellStyle.SOLID_FOREGROUND
     * @return
     */
    public static CellStyle setFillBackgroundColors(CellStyle cellStyle, short bg, short fg, short fp) {
        cellStyle.setFillBackgroundColor(bg);
        cellStyle.setFillForegroundColor(fg);
        cellStyle.setFillPattern(fp);
        return cellStyle;
    }

    /**
     * 设置字体
     *
     * @param wb
     * @return
     */
    public static Font createFonts(Workbook wb) {
        //创建Font对象
        Font font = wb.createFont();
        //设置字体
        font.setFontName("Arial");
        //着色
        font.setColor(HSSFColor.BLACK.index);
        //斜体
        font.setItalic(false);
        //字体大小
        font.setFontHeight((short) 200);
        return font;
    }

    /**
     *
     * @Title: copySheet
     * @Description: TODO 复制表格数据到新的表格中
     * @param fileName 文件名称包含文件完整路径
     * @param fromSheetIndex 要复制的表格下标，从0开始算
     * @param toSheetName  新表格的名称
     * @return: void
     * @throws
     * @author:  sl.qiu
     */
	public void copySheet(String fileName, int fromSheetIndex,
						  String toSheetName) {
		if (StringUtils.isBlank(fileName)) {
			return;
		}
		XSSFWorkbook wb = null;
	//	Sheet st =  null;
		FileInputStream inputStream=null;
		try {
			inputStream = new FileInputStream(fileName);
			wb = new XSSFWorkbook(inputStream);

			if (null == wb) {
				return;
			}
			if (null == wb.getSheetAt(fromSheetIndex)) {
				throw new RuntimeException("找不到下标为" + fromSheetIndex
						+ "Sheet");
			}
			wb.cloneSheet(0);
			wb.cloneSheet(fromSheetIndex);
			wb.cloneSheet(fromSheetIndex);

			wb.createSheet();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}finally {

			if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @Title: writeSheet
     * @Description:  TODO 写表格数据
     * @param sheet  表格对象
     * @return: void
     * @throws
     * @author:  sl.qiu
     */
	public void writeSheet(SXSSFSheet sheet) {
		// title
		if (null != fieldModel) {
			for (ExcelFieldModel fModel : fieldModel) {
				if (StringUtils.isBlank(fModel.getTitle())) {
					continue;
				}
				sheet.setColumnWidth(fModel.getColumn(), fModel.getWidth() * UNIT);
				fModel.setCellValue(fModel.getTitle());
                fModel.setRow(startRow);
                SXSSFRow rowItem = sheet.getRow(startRow);
				if (null == rowItem) {
                    rowItem = sheet.createRow(startRow);
				}
				wirteCell(fModel, rowItem);
			}
		}
		// cell
		if (null != this.cellModel) {
			Iterator<ExcelFieldModel> iter = cellModel.iterator();
            while (iter.hasNext()) {
                ExcelFieldModel fieldModel = iter.next();
				if (null == fieldModel) {
                    continue;
                }
                SXSSFRow rowItem = sheet.getRow(fieldModel.getRow());
                if (null == rowItem) {
                    rowItem = sheet.createRow(fieldModel.getRow());
				}
				wirteCell(fieldModel, rowItem);
			}
		}

		if (null != result) {
			// table
			int rowIndex = 1;
			Iterator<Map<String, Object>> iter = result.iterator();
			while (iter.hasNext()) {
				Map<String, Object> map = iter.next();
				startRow++;
				if (null == fieldModel) {
					continue;
				}
				for (ExcelFieldModel fModel : fieldModel) {
					if (null == fModel) {
						continue;
					}

					if (null == fModel.getFiled()) {
						continue;
					}
					Object value=map.get(fModel.getFiled().toLowerCase());
					if(value==null||value=="") {
						 value=map.get(fModel.getFiled());
					}
					fModel.setCellValue(value);
					fModel.setRow(startRow);
					if (ExcelFormat.ROW_NUMBER.equals(fModel.getFormat() != null ? fModel.getFormat().toUpperCase() : "")) {
                        fModel.setCellValue(rowIndex++);
                    }
                    SXSSFRow rowItem = sheet.getRow(startRow);
					if (null == rowItem) {
                        rowItem = sheet.createRow(startRow);
                    }
                    wirteCell(fModel, rowItem);
				}
			}
		}
	}

    /**
     * 创建Excel文件
     * <p>Title: create</p>
     * <p>Description: </p>
     * @return
     */
	@Override
    public String create() {
		wb = new SXSSFWorkbook();
		FileOutputStream os = null;
		//SXSSFRow row =null;
		try {
			if(StringUtils.isBlank(filePath)&&StringUtils.isBlank(getFileName())){
				return "";
			}
			if(StringUtils.isNotBlank(filePath)&&!filePath.endsWith("/")&&!filePath.endsWith("\\")){
				filePath=filePath+File.separatorChar;
			}
			String fileName = "";
			String ymd=DateUtil.getStringDateShort().replaceAll("-", "");
			String hms=DateUtil.getTimeShort().replaceAll(":", "");
			if(StringUtils.isNotBlank(getFileName())){
				fileName = filePath+getFileName()+".xlsx";
			}else{
				/*fileName =filePath+ System.currentTimeMillis() + "_" + r.nextInt()+".xlsx";*/
				fileName =filePath+ ymd + "_" + hms+Math.random()*10+".xlsx";
			}

			os=new FileOutputStream(new File(fileName));
			font = createFonts(wb);
			cellStyle = createStyleCell(wb);//创建边框样式
			cellStyleTitle = createStyleCell(wb);//创建边框样式
            cellStyleInteger = createStyleCell(wb);//创建数字的样式

            this.helper = wb.getCreationHelper();


			SXSSFSheet sheet= wb.createSheet(StringUtils.isBlank(sheetName)?"sheet1":sheetName);
            //定义样式

			writeSheet(sheet);
			// 关闭Excel工作薄对象
			startRow = 0;
			wb.write(os);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(),e);
		} finally {
			try {
				if (null != os) {
					os.flush();
                    os.close();
                }
                destory();
            } catch (IOException e) {
				e.printStackTrace();
            }

        }
    }

    /**
     *
     * @Title: update
     * @Description: TODO 更新表格数据
     * @param fileName 文件名称，包含文件的完整路径
     * @param sheetIndex 表格下标，从0开始计数
     * @return: String   返回文件名称
     * @throws
     * @author:  sl.qiu
     */
    public String update(String fileName, int sheetIndex) {
		return update( fileName, sheetIndex,null);
	}

    /**
     *
     * @Title: update
     * @Description: TODO 更新表格数据
     * @param fileName 文件名称，包含文件的完整路径
     * @param sheetIndex 表格下标，从0开始计数
     * @param sheetName 新的表格名称
     * @return: String   返回文件名称
     * @throws
     * @author:  sl.qiu
     */
	public String update(String fileName,int sheetIndex,String sheetName) {
		if(StringUtils.isBlank(fileName)){
			return null;
        }
        try {
            wb = (SXSSFWorkbook) WorkbookFactory.create(new File(fileName));
			SXSSFSheet sheet =null;
			if (newSheet) {
                sheet = wb.getSheetAt(sheetIndex);
                if (null != sheet) {
                    wb.removeSheetAt(sheetIndex);
                }
                if (StringUtils.isNotBlank(sheetName)) {
                    sheet = wb.createSheet(sheetName);

				} else {
                    sheet = wb.createSheet();
				}
			} else {
                sheet = wb.getSheetAt(sheetIndex);
			}
			font = createFonts(wb);
			cellStyle = createStyleCell(wb);// 创建边框样式
			cellStyleTitle   = createStyleCell(wb);// 创建边框样式
			cellStyleInteger = createStyleCell(wb);// 创建数字的样式

			this.helper = wb.getCreationHelper();
			this.writeSheet(sheet);
			// 关闭Excel工作薄对象
			startRow = 0;
			return fileName;
		} catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(),e);
		} finally {
			destory();
        }
    }

    /**
     *
     * @Title: wirteCell
     * @Description:  TODO 根据模板对象填写Excel的单元格
     * @param fModel 模板对象
     * @param row   要填写的行，从0开始算
     * @return: void
     * @throws
     * @author:  sl.qiu
     */
	public void wirteCell(ExcelFieldModel fModel,SXSSFRow row){
		if (null == fModel) {
			return ;
		}
		//if (null == fModel.getCellValue()) {
		//	return ;
		//}

		if(null == this.cellStyleMap){
			this.cellStyleMap  = new HashMap<String, CellStyle>();
		}


		String value = "";
		if (fModel.getCellValue() instanceof BigDecimal) {
			BigDecimal bigValue = (BigDecimal) fModel.getCellValue();
			value =String.valueOf(bigValue.doubleValue());
		} //Integer
		else if ( fModel.getCellValue() instanceof Integer) {
			Integer bigValue = (Integer) fModel.getCellValue();
			value = String.valueOf(bigValue);
		}
		else if ( fModel.getCellValue() instanceof Date) {
			Date bigValue = (Date) fModel.getCellValue();
			value =DateUtil.dispLong(bigValue);
		}
		else {
			value = String.valueOf( StrUtils.null2empty(fModel.getCellValue()));
		}
		String format = fModel.getFormat();
		Cell cell = row.createCell(fModel.getColumn());

		if (StringUtils.isNotBlank(format)&&StringUtils.isNotBlank(value.trim())&&!value.equals(fModel.getTitle())) {
			if ((format.toUpperCase().indexOf("Y") >= 0
					|| format.toUpperCase().indexOf("M") >= 0
					|| format.toUpperCase().indexOf("D") >= 0 )
					&& StringUtils.isNotBlank(value)
					&& !format.toUpperCase().equals(ExcelFormat.ROW_NUMBER)) {
				//cellStyleDate = setCellFormat( helper, this.cellStyleDate,format);
				Date date =null;
				try{
					date =  DateUtil.toDate(value);
					if(null == this.cellStyleMap.get(format)) {
						CellStyle    cellStyleDate=createStyleCell(wb);//创建数字的样式
						cellStyleDate.setDataFormat(wb.createDataFormat().getFormat(format));

						cellStyleDate.setFont(font);
						this.cellStyleMap.put(format,cellStyleDate);
					}

					cell.setCellStyle( this.cellStyleMap.get(format));
					cell.setCellValue(date);

				}catch (Exception e) {
					cell.setCellValue(value);
				}



			} else if (format.toUpperCase().indexOf("#") >= 0 ) {
				if(null == this.cellStyleMap.get(format)) {
					CellStyle cellStyleInt = createStyleCell(wb);//创建数字的样式
					cellStyleInt.setDataFormat(HSSFDataFormat.getBuiltinFormat(format));
					cellStyleInt.setFont(font);

					this.cellStyleMap.put(format,cellStyleInt);
				}
				cell.setCellStyle( this.cellStyleMap.get(format));
				if(StringUtils.isNotBlank(value)) {
					cell.setCellValue(new Double(value));
				}else{
					cell.setCellValue(new Double(0));
				}



			}else if (format.toUpperCase().endsWith(ExcelFormat.ROW_NUMBER)){
				cellStyleInteger= setCellFormat( helper, this.cellStyleInteger,"#");
				cell.setCellStyle(cellStyleInteger);
				cell.setCellValue(new Double(value));
			}
		} else {
			if (value.equals(fModel.getTitle())) {
				if(null == this.cellStyleMap.get("cellStyleTitle")) {
					CellStyle csTitle = setFillBackgroundColors(cellStyleTitle,
							IndexedColors.GOLD.getIndex(),
							IndexedColors.GOLD.getIndex(),
							CellStyle.SOLID_FOREGROUND);
					this.cellStyleMap.put("cellStyleTitle",csTitle);
				}
				cell.setCellStyle(this.cellStyleMap.get("cellStyleTitle"));
			} else {
				if(null == this.cellStyleMap.get("cellStyle")) {
					CellStyle cs = setFillBackgroundColors(cellStyle,
							IndexedColors.WHITE.getIndex(),
							IndexedColors.WHITE.getIndex(),
							CellStyle.SOLID_FOREGROUND);

					cs.setFont(font);
					this.cellStyleMap.put("cellStyle", cs);
                }
                cell.setCellStyle(this.cellStyleMap.get("cellStyle"));
            }
            cell.setCellValue(value);
		}
	}

   /**
    *
    * @Title: destory
    * @Description:  TODO 创建完Excel文件后的销毁方法
    * @return: void
    * @throws
    * @author:  sl.qiu
    */
    public void destory(){
		if(null != this.fieldModel){
			this.fieldModel.clear();
		}
		if(null != this.cellModel){
			this.cellModel.clear();
		}
		if(null != this.cellStyleMap){
			this.cellStyleMap.clear();
		}
	}

	public List<ExcelFieldModel> getFieldModel() {
		return fieldModel;
	}

	@Override
    public void setFieldModel(List<ExcelFieldModel> fieldModel) {
		this.fieldModel = fieldModel;
	}

	public List<Map<String,Object>> getResult() {
		return result;
	}

	@Override
    public void setResult(List<Map<String,Object>> result) {
		this.result = result;
	}

	public String getFilePath() {
		return filePath;
	}

	@Override
    public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public boolean isNewSheet() {
		return newSheet;
	}

	public void setNewSheet(boolean newSheet) {
		this.newSheet = newSheet;
	}

	public String getFileName() {
		return fileName;
	}

	@Override
    public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<ExcelFieldModel> getCellModel() {
		return cellModel;
	}




	/*
	/ 设置页边距，注意参数0.5d是英寸，设置完后，打开Excel时，就会变成1.3厘米
	ws.getSettings().setBottomMargin(0.5d);
	ws.getSettings().setTopMargin(0.5d);
	ws.getSettings().setLeftMargin(0.5d);
	ws.getSettings().setRightMargin(0.5d);

	// 设置页面方向，参数中的两个0.5d分别是页眉和页脚的边距
	// 横向：
	ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A3,0.5d,0.5d);
	// 纵向：
	ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A3,0.5d,0.5d);

	// 设置页面的缩放比例，参数为百分比
	ws.getSettings().setScaleFactor(42);

	// 设置页码
	ws.setFooter("", "&P", "");

	// 设置冻结单元格
	ws.getSettings().setVerticalFreeze(8);

	// 设置打印标题单元格
	ws.getSettings().setPrintTitlesRow(0, 7);

	//　设置密码
	ws.getSettings().setProtected(true);
	ws.getSettings().setPassword("mima");
	*/

	public void setCellModel(List<ExcelFieldModel> cellModel) {
        this.cellModel = cellModel;
	}


	/*public static void main(String[] args) throws IOException {
        //创建Workbook对象（这一个对象代表着对应的一个Excel文件）
                     //HSSFWorkbook表示以xls为后缀名的文件
		XSSFWorkbook wb = new XSSFWorkbook();
        //获得CreationHelper对象,这个应该是一个帮助类
        CreationHelper helper = wb.getCreationHelper();
        //创建Sheet并给名字(表示Excel的一个Sheet)
        XSSFSheet sheet1 = wb.createSheet("HSSF_Sheet_1");
        XSSFSheet sheet2 = wb.createSheet("HSSF_Sheet_2");
        //Row表示一行Cell表示一列
        XSSFRow row = null;
        XSSFCell cell = null;
        for(int i=0;i<60;i=i+2){
            //获得这个sheet的第i行
            row = sheet1.createRow(i);
            //设置行长度自动
            //row.setHeight((short)500);
            row.setHeightInPoints(20);
            //row.setZeroHeight(true);
            for(int j=0;j<25;j++){
                //设置每个sheet每一行的宽度,自动,根据需求自行确定
                sheet1.autoSizeColumn(j+1, true);
                //创建一个基本的样式
                CellStyle cellStyle = CreateExcel.createStyleCell(wb);
                //获得这一行的每j列
                cell = row.createCell(j);
                if(j==0){
                    //设置文字在单元格里面的位置
                    cellStyle = CreateExcel.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
                    //先创建字体样式,并把这个样式加到单元格的字体里面
                    cellStyle.setFont(createFonts(wb));
                    //把这个样式加到单元格里面
                    cell.setCellStyle(cellStyle);
                    //给单元格设值
                    cell.setCellValue(true);
                }else if(j==1){
                    //设置文字在单元格里面的位置
                    cellStyle = CreateExcel.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
                    //设置这个样式的格式(Format)
                    cellStyle = CreateExcel.setCellFormat(helper,cellStyle, "#,##0.0000");
                    //先创建字体样式,并把这个样式加到单元格的字体里面
                    cellStyle.setFont(createFonts(wb));
                    //把这个样式加到单元格里面
                    cell.setCellStyle(cellStyle);
                    //给单元格设值
                    cell.setCellValue(new Double(2008.2008));
                }else if(j==2){
                    cellStyle = CreateExcel.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
                    cellStyle.setFont(createFonts(wb));
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(helper.createRichTextString("RichString"+i+j));
                }else if(j==3){
                    cellStyle = CreateExcel.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
                    cellStyle = CreateExcel.setCellFormat(helper,cellStyle, "MM-yyyy-dd");
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(new Date());
                }else if(j==24){
                    cellStyle = CreateExcel.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
                    cellStyle.setFont(createFonts(wb));
                    //设置公式
                    cell.setCellFormula("SUM(E"+(i+1)+":X"+(i+1)+")");
                }else{
                    cellStyle = CreateExcel.setCellStyleAlignment(cellStyle, CellStyle.ALIGN_CENTER, CellStyle.VERTICAL_CENTER);
                    cellStyle = CreateExcel.setFillBackgroundColors(cellStyle,IndexedColors.ORANGE.getIndex(),IndexedColors.ORANGE.getIndex(),CellStyle.SOLID_FOREGROUND);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(1);
                }
            }
        }
        //输出
        OutputStream os = new FileOutputStream(new File("D://SummaryHSSF.xls"));
        wb.write(os);
        os.close();
    }
	*/

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

	public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
	}

	/**
	 * 边框
	 * @param wb
	 * @return
	 */
	public  CellStyle createStyleCell(Workbook wb){
		CellStyle  cellStyle = wb.createCellStyle();
		//设置一个单元格边框颜色
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		//设置一个单元格边框颜色
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return cellStyle;
	}
}
