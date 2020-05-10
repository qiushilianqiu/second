package com.gantang.common.excel;


import com.gantang.common.util.CollectionUtil;
import com.gantang.common.util.DateUtil;
import jxl.Cell;
import jxl.CellType;
import jxl.Workbook;
import jxl.biff.XFRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.eccel
 * @ClassName(类名称):ReadExcelJxl
 * @Title(标题):  ReadExcelJxl.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午5:26:20   
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
public class ReadExcelJxl  extends  ReadExcel {

    private String path;//读取文件的路径
    private InputStream is;//读取文件的文件流
    private List<ExcelFieldModel> modelTable;//行读取的数据模板
    private List<ExcelFieldModel> modelCell;//单元格读取的数据模板
    private List<Map<String,String>> resultTable = new ArrayList<Map<String,String>>();
    private Map<String, String> resultMap = new HashMap<String, String>();
    private Map<Integer, String> titleMap = new HashMap<Integer, String>();   //标题

    private int startRow = 0; //读取的起始行，从0开始计数，默认为0
    private int titleRow = 0; //读取标题所在的行，从0开始计数，默认为0
    private int sheetIndex = 0;//读取表格的下标，从0开始计数，默认为0
    private String sheetName;//读取表格的名称
    private int columnsCount=-1;//整个Excel文件的总列数，初始为-1
    private int rowsCount=-1;//整个Excel文件的总条数，初始为-1

    /**
     * test
     *
     * @param arges
     */
    public static void main(String[] arges) {
        try {
            ReadExcel r = new ReadExcelJxl();
            // r.setSheetName("Sheet1");
            r.setPath("D://RMA--.xls");

            List<ExcelFieldModel> list = new ArrayList<ExcelFieldModel>();
            list.add(new ExcelFieldModel("STORER_CODE", 0, ExcelFormat.NOT_NULL));
            list.add(new ExcelFieldModel("SKU_CODE", 1, ExcelFormat.NOT_NULL));
            list.add(new ExcelFieldModel("SKU_ALIAS", 2));
            list.add(new ExcelFieldModel("NEW_OLD_FlAG", 3));//新旧货(N/R)
            list.add(new ExcelFieldModel("REC_NO", 4));//入库单号
            list.add(new ExcelFieldModel("INVOICE_NO", 5));//发票号
            list.add(new ExcelFieldModel("RMA_NO", 6));//RMA_NO
            list.add(new ExcelFieldModel("MAINBILL_NO", 7));//主单号
            list.add(new ExcelFieldModel("SUBBILL_NO", 8));//分单号
            list.add(new ExcelFieldModel("WAREHOUSE_CODE", 9));//
            list.add(new ExcelFieldModel("LOCATION_CODE", 10));//
            list.add(new ExcelFieldModel("TRAY_QTY", 11));//托数
            list.add(new ExcelFieldModel("TRAY_BOX", 12));//箱/托
            list.add(new ExcelFieldModel("PACKAGE_QTY", 13));//件/箱
            list.add(new ExcelFieldModel("UNIT", 14));//单位
            list.add(new ExcelFieldModel("SKU_ATTRIBUTE", 15));//货物属性
            list.add(new ExcelFieldModel("BARCODE", 16));//条码
            list.add(new ExcelFieldModel("VERSION", 17));//版本号

            r.setModelTable(list);
            List<Map<String, String>> result = r.getResultTable();
            if (result != null) {

                Iterator<Map<String, String>> iter = result.iterator();
                while (iter.hasNext()) {
                    Map<String, String> map = iter.next();
                    System.out.println(map.get("STORER_CODE") + "--" + map.get("SKU_CODE")
                            + "--" + map.get("SKU_ALIAS") + "--" + map.get("NEW_OLD_FlAG"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isOutOfArray(jxl.Sheet st, int x, int y) {
        return x > st.getColumns() - 1 || y > st.getRows() - 1;
    }

    public String readRow(jxl.Sheet st, int x, int y) {
        return isOutOfArray(st, x, y) ? "" : cell(st.getCell(x, y), null);
    }

    public String readRow(jxl.Sheet st, int x, int y, String format) {
        return isOutOfArray(st, x, y) ? "" : cell(st.getCell(x, y), format);
    }

    public String readRow(jxl.Sheet st, ExcelFieldModel fieldModel) {
        if(isOutOfArray(st, fieldModel.getColumn(), fieldModel.getRow())){
            return "" ;
        }else{

            Cell cell =  st.getCell(fieldModel.getColumn(), fieldModel.getRow());
            if(cell !=null){
                return  cell(cell,fieldModel.getFormat());
            }else{
                return "";
            }
        }
    }

    public String cell(Cell cell, String format) {
        String cellvolue = "";
        if (null == cell) {
            return cellvolue;
        }
        if (CellType.LABEL == cell.getType()) {
            jxl.LabelCell c = (jxl.LabelCell) cell;
            cellvolue = c.getString().trim();
            if (null != format && format.toUpperCase().indexOf("Y") >= 0){
                try{

                 String date =  DateUtil.getDateLong(cellvolue) ;
                 cellvolue =  DateUtil.format(date, format);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }

        } else if (CellType.DATE == cell.getType()) {
            SimpleDateFormat sdf;
            if (null != format && format.toUpperCase().indexOf("Y") >= 0) {
                sdf = new SimpleDateFormat(format);
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
            jxl.DateCell c = (jxl.DateCell) cell;
            Date date = c.getDate();
            date = DateUtil.hourAdd(date, -8);// default -8 hour
            return sdf.format(date);
        } else if (CellType.NUMBER == cell.getType()) {
            DecimalFormat df;
            if (null != format && format.indexOf("#") >= 0) {
                df = new DecimalFormat(format);
            } else {
                df = new DecimalFormat("#");
            }

            jxl.NumberCell c = (jxl.NumberCell) cell;

            XFRecord xfr = (XFRecord) c.getCellFormat(); //

            if ((null != format && format.toUpperCase().indexOf("Y") >= 0)&&(xfr.formatIndex == 58 || xfr.formatIndex == 179
                    || xfr.formatIndex == 176 || xfr.formatIndex == 184
                    || xfr.formatIndex == 185 || xfr.formatIndex == 186)) {
                Date date = HSSFDateUtil.getJavaDate(c.getValue());
                SimpleDateFormat sdf;

                sdf = new SimpleDateFormat(format);

                return sdf.format(date);
            }

            return df.format(c.getValue());
        } else if (CellType.BOOLEAN == cell.getType()) {
            jxl.BooleanCell c = (jxl.BooleanCell) cell;
            return c.getValue() ? "true" : "false";
        } else if (CellType.EMPTY == cell.getType()) {
            return "";
        } else if (CellType.ERROR == cell.getType()) {
            return "";
        }
		/*
		 * else if(CellType.DATE_FORMULA==cell.getType()){ jxl.DateFormulaCell
		 * c=(jxl.DateFormulaCell)cell; try{
		 *
		 * return c.getFormula(); }catch(Exception e){ e.printStackTrace(); } }
		 */

        return cellvolue;

    }

    @Override
    public void read() {
        read(ReadExcel.DATA_TYPE_ALL);//全部读取
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
        jxl.Workbook wb = null;
        try {
             wb = Workbook.getWorkbook(is);
            jxl.Sheet st = wb.getSheet(this.sheetIndex);
            if (!StringUtils.isBlank(this.sheetName)) {
                st = wb.getSheet(this.sheetName);
                if (st == null) {
                    throw new RuntimeException("can't not find sheet name of '"
                            + this.sheetName + "'!");
                }
            }
            this.sheetName =	 st.getName();
            int rows = st.getRows();
            this.setRowsCount(rows);
            this.setColumnsCount(st.getColumns());
            if(ReadExcel.DATA_TYPE_CELL.equals(dataType) || ReadExcel.DATA_TYPE_ALL.equals(dataType)){
                readResultMap(st);
            }
            if(ReadExcel.DATA_TYPE_TITLE.equals(dataType) || ReadExcel.DATA_TYPE_ALL.equals(dataType)){
                readTitleMap(st);
            }
            if(ReadExcel.DATA_TYPE_TABLE.equals(dataType) || ReadExcel.DATA_TYPE_ALL.equals(dataType)){
                readResultTable(st, rows);
            }
//            readTitleMap(st); //读取标题
//            readResultMap(st);
//            readResultTable(st, rows);//读取数据

        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {

            try {

                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @Title: readTitleMap
     * @Description:  TODO 读取Excel的标题数据
     * @param st 表格对象
     * @return: void
     * @throws
	 * @author:  sl.qiu
	 */
    private void readTitleMap(jxl.Sheet st){
          if(this.getRowsCount()<=0){
               return ;
          }
          int columns = this.getColumnsCount();
        if(columns<=0){
            return ;
        }
        for(int i = 0;i< columns;i++){
            ExcelFieldModel fieldModel = new ExcelFieldModel("TITLE_"+i,i,getTitleRow());
            String cellValue = readRow(st, fieldModel);
            titleMap.put(i, cellValue);
        }
    }

    /**
     * @Title: readResultMap
     * @Description: TODO 根据单元格数据模板，读取表格单元格数据
     * @param st 表格对象
     * @return: void
     * @throws
	 * @author:  sl.qiu
	 */
    private void readResultMap(jxl.Sheet st) {
        if (this.modelCell == null)
            return;
        Iterator<ExcelFieldModel> iter = modelCell.iterator();
        while (iter.hasNext()) {
            ExcelFieldModel fieldModel = iter.next();
            if (null == fieldModel)
                continue;
            if (null == fieldModel.getFiled())
                continue;

            String cellValue = readRow(st, fieldModel);
            resultMap.put(fieldModel.getFiled(), cellValue);

        }
    }

    /**
     *
     * @Title: readResultTable
     * @Description:  TODO 根据表格数据模板，读取表格所有行数据
	 * @param st 表格对象
     * @param rows   行
     * @return: void
     * @throws
	 * @author:  sl.qiu
	 */
    private void readResultTable(jxl.Sheet st, int rows) {
        if (null == this.modelTable)
            return;
        if(this.startRow == 0){
            this.startRow = this.startRow + 1;
        }
        if(this.startRow == -1){
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



                map.put(fieldModel.getFiled(), cellVolue);
            }
            if (CollectionUtil.isNullMap(map))
                continue;
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
    public  int getTitleRow(){
        return titleRow;
    }

    @Override
    public  void setTitleRow(int titleRow){
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
    public List<Map<String,String>> getResultTable() {

            read(ReadExcel.DATA_TYPE_TABLE);
            return resultTable;


    }

    @Override
    public Map<String,String> getResultMap() {
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
        return this.modelTable ;
    }

    @Override
    public void setModelTable(List<ExcelFieldModel> modelTable) {
        this.modelTable = modelTable;
    }
   
    @Override
    public void setModelCell(List<ExcelFieldModel> modelCell) {
        this.modelCell = modelCell;
    }

	/*private void updateCount(){
        if (null == is) {
            return;
        }
        try {

            jxl.Workbook wb = Workbook.getWorkbook(is);
            jxl.Sheet st = wb.getSheet(this.sheetIndex);
            if (!StringUtils.isBlank(this.sheetName)) {
                st = wb.getSheet(this.sheetName);
                if (st == null) {
                    throw new RuntimeException("can't not find sheet name of '"
                            + this.sheetName + "'!");
                }
            }
            int rows = st.getRows();
            this.setRowsCount(rows);
            this.setColumnsCount(st.getColumns());
        }catch (Exception e){

        }
    }*/
    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
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
