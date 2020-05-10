
package com.gantang.common.sysimport;

import java.io.File;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.configurer
 * @ClassName(类名称):MvcConfig
 * @Title(标题):  MvcConfig.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月24日 下午4:16:48   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):导入导出相关报表   
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
public class MvcConfig {

    /**
     * 用保存系统生成的报表的根目录
     */
    //public static String REPORT_PATH = "D:/SMVC_FLODER" + File.separatorChar;
   /* public  static String REPORT_PATH="/web/SCM_FLODER" +File.separatorChar;*/
	//导入模板存放地
	public  static String REPORT_PATH_IMPORT_TEMPLATE="importTemplate/";
	//导入出错报表存放地
	public  static String REPORT_PATH_IMPORT_ERROR_RESULT="importError/";
	//生成报表存放地
	public  static String REPORT_PATH_IMPORT_REPORT="exportReport/";
    public static final String FILE_FOLDER_CODE_LIST = "FILE_FOLDER";
    /**
     * 报表的根目录文件的有效天数，默认为10天，超过10天的报表，系统将自动删除
     */
    public static int REPORT_VALIDITY_DATE = 10;

    public static String JRXML_PATCH = "";

    public static String REPORT_FTL_PATCH = "";
    //创建报表的Finder序列化路径 ,baseDaoImpl中会使用到
    public static String FINDER_SERIALIZE_PATCH=REPORT_PATH_IMPORT_TEMPLATE+"SERIALIZE_FINDER"+ File.separatorChar;



}
