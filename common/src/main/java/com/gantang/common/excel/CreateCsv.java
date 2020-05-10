
package com.gantang.common.excel;


import com.gantang.common.util.DateUtil;
import com.gantang.common.util.StrUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.*;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.eccel
 * @ClassName(类名称):CreateCsv
 * @Title(标题):  CreateCsv.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午5:26:52   
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
public class CreateCsv implements ExcelFormat {
    static Random r = new Random();
    private List<ExcelFieldModel> fieldModel;  //行格式读取模板对象
    private List<ExcelFieldModel> cellModel;  //单元格格式模板对象
    private List<Map<String,Object>> result;  //要填写的集合数据
    private String filePath; //文件路径
    private String fileName; //文件名称
    private File file; //文件对象,如果文件对象不为空，先使用文件对象进行创建，反之使用文件名称创建
    private int startRow=0; //写入的起始行，从0开始计数，默认为0

    private boolean update = false;//是否为追加写，默认为false,true的话会进行追加写

   /**
    * 
    * <p>Title: create</p>   
    * <p>Description: </p>   
    * @return
    */
    @Override
    public String create() {
        FileOutputStream fs =null;//实例了文件输出流，参数是文件输出路径
        OutputStreamWriter ow = null;//在写输出流的时候做编码格式转化，以免乱码！
        BufferedWriter bufferedWriter = null;//将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入



        if(StringUtils.isBlank(filePath)&&StringUtils.isBlank(getFileName()) && file ==null){
            return null;
        }
        if(StringUtils.isNotBlank(filePath)&&!filePath.endsWith("/")&&!filePath.endsWith("\\")){
            filePath=filePath+File.separatorChar;
        }
        String fileName = "";
        if(StringUtils.isNotBlank(getFileName())){
            fileName = getFileName();
        }else{
            fileName = filePath+ System.currentTimeMillis() + "_" + r.nextInt()+".csv";
        }
        // 排序
        sortExcelFieldModel();
        try {
            if (!update) {
                // 覆盖原有文件
                if(null != file){
                    fs = new FileOutputStream(file);//实例了文件输出流，参数是文件输出路径
                    ow = new OutputStreamWriter(fs,"GBK"); //在写输出流的时候做编码格式转化，以免乱码！

                }else{

                    fs = new FileOutputStream(new File(fileName));//实例了文件输出流，参数是文件输出路径
                    ow = new OutputStreamWriter(fs,"GBK"); //在写输出流的时候做编码格式转化，以免乱码！
                }
            } else {
                // 在原有文件上追加数据
                if(null != file){
                    fs = new FileOutputStream(file,true);//实例了文件输出流，参数是文件输出路径
                    ow = new OutputStreamWriter(fs,"GBK"); //在写输出流的时候做编码格式转化，以免乱码！
                }else{
                    fs = new FileOutputStream(new File(fileName),true);//实例了文件输出流，参数是文件输出路径
                    ow = new OutputStreamWriter(fs,"GBK"); //在写输出流的时候做编码格式转化，以免乱码！
                }
            }
            bufferedWriter =  new BufferedWriter(ow);

            // title
            if (null != fieldModel && !update) {
                int columnIndex = 0;
                StringBuilder titelStr = new StringBuilder();
                for (ExcelFieldModel fModel : fieldModel) {
                    String cellEmptyStr = getColumnStr(columnIndex, fModel);
                    if (StringUtils.isNotBlank(cellEmptyStr)) {
                        titelStr.append(",").append(cellEmptyStr);
                        columnIndex = fModel.getColumn();
                    }
                    titelStr.append(",\"").append(StrUtils.null2empty(fModel.getTitle())).append("\"");
                    columnIndex++;
                }
                if (titelStr.toString().length() > 0) {
                    titelStr.append("\r\n");
                    bufferedWriter.write(titelStr.toString().substring(1));
                }
            }
            // cell ,cvs 一般不会用到
			/*
			 * if ( null != this.cellModel ) { Iterator<ExcelFieldModel> iter =
			 * cellModel.iterator(); while (iter.hasNext()) { ExcelFieldModel
			 * fieldModel = iter.next(); if (null == fieldModel) continue;
			 * wirteCell(fieldModel); } }
			 */

            // table
            Iterator<Map<String, Object>> iter = result.iterator();

            while (iter.hasNext()) {
                Map<String, Object> map = iter.next();
                StringBuilder rowStr = new StringBuilder();
                startRow++;
                if (null == fieldModel) {
                    continue;
                }

                int columnIndex = 0;
                for (ExcelFieldModel fModel : fieldModel) {
                    if (null == fModel) {
                        fModel = new ExcelFieldModel("", columnIndex);
                    }
                    fModel.setRow(startRow);// 设置行号
                    String cellEmptyStr = getColumnStr(columnIndex, fModel);
                    if (StringUtils.isNotBlank(cellEmptyStr)) {
                        rowStr.append(",").append(cellEmptyStr);
                        columnIndex = fModel.getColumn();
                    }
                    if (StringUtils.isBlank(fModel.getFiled())) {
                        fModel.setCellValue("");
                    } else {
                        fModel.setCellValue(map.get(fModel.getFiled()));
                    }

                    String cellStr = wirteCell(fModel);
                    rowStr.append(",").append(cellStr);
                    columnIndex++;
                }

                if (rowStr.toString().length() > 0) {
                    rowStr.append("\r\n");
                    bufferedWriter.write(rowStr.toString().substring(1));
                }

                if(startRow%50 ==0){
                    bufferedWriter.flush();
                }
            }

            this.startRow = 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                if (ow != null) {
                    ow.flush();
                    ow.close();
                }
                if (fs != null) {
                    fs.flush();
                    fs.close();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /**
     * 
     * @Title: getColumnStr   
     * @Description:  TODO(这里描述这个方法做什么 – 可选) 
     * @param index
     * @param fModel    
     * @return: String      
     * @throws
     * @author:  sl.qiu
     */
    public String getColumnStr(int index,ExcelFieldModel fModel){
    	if(fModel==null) {return null;}  
        StringBuilder sBuilder= new StringBuilder();
        int columnIndex = fModel.getColumn();
        if(columnIndex!= index){
            if(columnIndex - index > 0){
                for( int i=index;i< columnIndex ;i++){
                    sBuilder.append(",").append("\"").append("\"");
                }
                return sBuilder.toString().substring(1);
            }else{
                sBuilder.append(",").append("\"").append("\"");
                return sBuilder.toString().substring(1);
            }

        }

        return null;
    }

    /**
     * 
     * @Title: sortExcelFieldModel   
     * @Description: TODO 对模板对象进行排序      
     * @return: void      
     * @throws
     * @author:  sl.qiu
     */
    public void sortExcelFieldModel(){
        if(null == fieldModel){
            return ;
        }
        Collections.sort(fieldModel, new Comparator<ExcelFieldModel>() {
            @Override
            public int compare(ExcelFieldModel u1, ExcelFieldModel u2) {
                int l = u1.getColumn()-u2.getColumn();
                return l;
            }
        });
    }
    /**
     * 
     * @Title: wirteCell   
     * @Description: TODO 写入单元格数据
     * @param fModel 模板数据对象    
     * @return: String  文件名    
     * @throws
     * @author: sl.qiu
     */
    public String wirteCell(ExcelFieldModel fModel) {
        StringBuilder sBuilder= new StringBuilder();
        if (null == fModel) {
            return "\"\"";
        }
        String value = String.valueOf( StrUtils.null2empty(fModel.getCellValue()));
        String format = fModel.getFormat();
        if (StringUtils.isNotBlank(format)  ) {
            if ((format.toUpperCase().indexOf("Y") >= 0
                    || format.toUpperCase().indexOf("M") >= 0
                    || format.toUpperCase().indexOf("D") >= 0 )
                    && StringUtils.isNotBlank(value)) {
                try{
                    //2017-11-22 19:42:39 
                	if(value.length()>19) {
                		value = value.substring(0, 19);
                	}
                    value = DateUtil.format(value,format);
                    sBuilder.append(value);
                }catch(Exception e){
                    e.printStackTrace();
                    value = "\""+value+"\"";
                }
            } else if (format.toUpperCase().indexOf("#") >= 0
                    && StringUtils.isNotBlank(value)) {
                if(StrUtils.isDouble(value)){
                    DecimalFormat df=new DecimalFormat(format);
                    value = df.format(Double.valueOf(value));
                }else{
                    value = "\""+value+"\"";
                }
                sBuilder.append(value);

            }else if (format.toUpperCase().endsWith(ExcelFormat.ROW_NUMBER)){
                sBuilder.append(fModel.getRow()).append("");
            }
        } else {
            sBuilder.append("\"").append(value).append("\"");
        }
        return sBuilder.toString();
    }

  
    public List<ExcelFieldModel> getFieldModel() {
        return fieldModel;
    }
    @Override
    public void setFieldModel(List<ExcelFieldModel> fieldModel) {
        this.fieldModel = fieldModel;
    }
    public List<ExcelFieldModel> getCellModel() {
        return cellModel;
    }
    public void setCellModel(List<ExcelFieldModel> cellModel) {
        this.cellModel = cellModel;
    }
    public List<Map<String, Object>> getResult() {
        return result;
    }
    @Override
    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }
    public String getFilePath() {
        return filePath;
    }
    @Override
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName() {
        return fileName;
    }
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getStartRow() {
        return startRow;
    }
    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }


    public boolean isUpdate() {
        return update;
    }


    public void setUpdate(boolean update) {
        this.update = update;
    }


    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }



}