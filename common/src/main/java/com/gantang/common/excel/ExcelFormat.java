package com.gantang.common.excel;

import java.util.List;
import java.util.Map;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):oms-common
 * @Package(包名称) com.eclp.excel  
 * @ClassName(类名称):ExcelFormat
 * @Title(标题):  ExcelFormat.java   
 * @see(与该类相关联的类):  
 * @author(作者): 深圳汇合投资发展有限公司    sl.qiu 
 * @since: JDK1.8
 * @date(创建日期):   2017年11月3日 上午9:54:07   
 * @version(版本): V1.0 
 * @Copyright(版权): 2017 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):   
 * TODO 读写Excel或写入CSV文件的格式类型接口
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
public interface ExcelFormat {
    String DATE_LONGER = "yyyy-MM-dd HH:mm:ss";
    String DATE_LONG = "yyyy-MM-dd HH:mm";
    String DATE_SHORT = "yyyy-MM-dd";
    String DOUBLE_SHORT = "#";
    String DOUBLE_LONG = "#.##";
    String NOT_NULL = "NOTNULL";

    String FORMULA = "FORMULA";
    String ROW_NUMBER = "ROWNUMBER";

    //public final static float  UNIT = 15.625f;
    int UNIT = 100;

    String REPORT_TYPE_EXCEL = "EXCEL";
    String REPORT_TYPE_CSV = "CSV";

    void setFilePath(String filePath);

    void setFileName(String fileName);

    void setResult(List<Map<String, Object>> result);

    void setFieldModel(List<ExcelFieldModel> fieldModel);
    /**
     * 
     * @Title: create   
     * @Description: TODO 该方法用于根据实现类生产Excel或者CSV文件
     * @return: String  产生的文件名  
     * @throws
     * @author: 深圳汇合投资发展有限公司 sl.qiu
     */
    String create();

}
