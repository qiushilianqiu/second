package com.gantang.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util
 * @ClassName(类名称):F
 * @Title(标题):  F.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午6:00:22   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  读取数据库数据时的字段条件对象，用于动态生成条件SQL 
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
public class F implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/** 字段名称 **/
	private String field;
	/** 字段的值 **/
	private Object param;
	/**查询标准**/
	private String criteria = EQ;
	
	private String logic=" AND ";
	
   private boolean isParams=true;

	
	private F[] f;//用于一组一组的or条件,如  or( )



	public static final String LIKE=" LIKE ";
	public static final String EQ=" = ";//等于
	public static final String LG=" > ";//大于
	public static final String SM=" < ";//小于
	public static final String LE=" >= ";//大于等于
	public static final String NEQ=" <> ";//小于等于
	public static final String SE=" <= ";//小于等于

	public static final String IN=" IN ";//


	public static final String EQ_STR ="EQ";//	等于
	public static final String LE_STR ="LE";//	大于等于
	public static final String LG_STR ="LG";//	大于
	public static final String NEQ_STR ="NEQ";//	不等于
	public static final String SE_STR ="SE";//	小于等于
	public static final String SM_STR ="SM";//	小于
	public static final String LIKE_STR ="LIKE";//	全模糊查找
	public static final String LIKEL_STR ="LIKEL";//	左模糊查找
	public static final String LIKER_STR ="LIKER";//	右模糊查找
 //=======================================================
	public static final String IN_STR ="IN";//	包含(多个换行隔开)
	public static final String NIN_STR ="NIN";//	不包含(多个换行隔开)
	public static final String NNU_STR ="NNU";//	不为空
	public static final String NU_STR ="NU";//	为空


	public static Map<String,String>  CRITERIA_MAP =new HashMap<String, String>();

	
	 public final static String USE_STATUS_YES="Y";//使用状态 Yes
	 public final static String USE_STATUS_NO="N";//使用状态 No
	 

    public final static String USE_STATUS_YES_SQLSTR="='Y'";//使用状态 Yes
	static {
		CRITERIA_MAP.put(EQ_STR,EQ);
		CRITERIA_MAP.put(LE_STR,LE);
		CRITERIA_MAP.put(LG_STR,LG);
		CRITERIA_MAP.put(NEQ_STR,NEQ);
		CRITERIA_MAP.put(SE_STR,SE);
		CRITERIA_MAP.put(SM_STR,SM);
		CRITERIA_MAP.put(LIKE_STR,LIKE);
		CRITERIA_MAP.put(LIKEL_STR,LIKE);
		CRITERIA_MAP.put(LIKER_STR,LIKE);
	}
	/**
	 * @Description:  TODO  构造函数
	 * @author:仇石廉
	 */
	public F() {

	}
    /**
     * @Description:  TODO 构造函数 用于生成一组括号
     * @author:仇石廉
     * @param f
     */
	public F(F[] f) {
       this.f =f;
	}
	  /**
     * @Description:  TODO 用于生成一组括号
     * @author:仇石廉
     * @param f
     */
	public F(F[] f,String logic) {
       this.f =f;
       this.logic = logic;
	}

	/**
	 * @Description:  TODO 构造函数
     * @author:仇石廉
	 * @param field  字段名称
	 * @param param  字段值
	 */
	public F(String field, Object param) {
		this.field = field;
		this.param = param;
		this.logic = " AND ";
	}
	/**
	 * @Description:  TODO 构造函数
     * @author:仇石廉
	 * @param field  字段名称
	 * @param param  字段值
	 * @param isParams  是否为参数
	 */
    public F(String field, Object param, boolean isParams) {
        this.field = field;
        this.param = param;
        this.isParams = isParams;
    }
    /**
	 * @Description:  TODO 构造函数
     * @author:仇石廉
	 * @param sql  SQL部分代码
	 */
    public F(String sql) {
        this.field = sql;
        this.isParams = false;
    }
    /**
	 * @Description:  TODO 构造函数
	 * @author:仇石廉
	 * @param field  字段名称
	 * @param param  字段值
	 * @param logic  是否为自动增长
	 */
	public F(String field, Object param, String logic) {
		this.field = field;
		this.param = param;
		this.logic = logic;

	}
	/**
	 * @Description:  TODO 构造函数
	 * @author:仇石廉
	 * @param field  字段名称
	 * @param criteria  查询条件运算符
	 * @param param  字段值
	 */
	public F(String field,  String criteria,Object param ) {
		this.field = field;
		this.param = param;
		this.criteria  = criteria;

	}
	/**
	 * @Description:  TODO 构造函数
	 * @author:仇石廉
	 * @param field  字段名称
	 * @param criteria  查询条件运算符
	 * @param param  字段值
	 */
	public F(String field,  String criteria,String param ) {
		this.field = field;
		this.param = param;
		this.criteria  = criteria;

	}
	/**
	 * @Description:  TODO 构造函数
	 * @author:仇石廉
	 * @param field  字段名称
	 * @param criteria  查询条件运算符
	 * @param param  字段值
	 * @param logic 运算逻辑 or 或者 and
	 */
	public F(String field, String criteria,Object param, String logic ) {
		this.field = field;
		this.param = param;
		this.criteria  = criteria;
		this.logic = logic;
	}

	
	/**
	 * @Title: getUpdateStr   
	 * @Description: TODO 获取更新部分的SQL方法 
	 * @return: String      
	 * @throws
	 * @author: sl.qiu
	 */
	public String getUpdateStr(){
        if(this.isParams() && this.param!=null){
            return new StringBuilder().append(this.field).append("=?").toString();
        }
        else{
            return this.field;
        }
	}
	/**
	 * @Title: getUpdateStr   
	 * @Description: TODO 获取条件部分的SQL方法 
	 * @return: String      
	 * @throws
	 * @author: sl.qiu
	 */
	public String getWhereStr(){
        if(this.isParams() && this.param!=null){
            return new StringBuilder().append(this.field).append(criteria).append("?").toString();
        }{
            return this.field;
        }
	}
	
	/**
	 * @Title: getUpdateStr   
	 * @Description: TODO 获取更新部分的SQL方法 
	 * @return: String      
	 * @throws
	 * @author: sl.qiu
	 */
	public String getWhereStr(String filedAlias){
		StringBuilder  sb= new StringBuilder();
		if(StringUtils.isNotBlank(filedAlias)){
			sb.append(StrUtils.null2empty(filedAlias)).append(".");
			sb.append(this.field);
		}else{
			sb.append(this.field);
		}
        if(this.isParams() && this.param!=null){
            sb.append(criteria).append("?");
        }

		
		return sb.toString();
		
	}
	
	/**
	 * @Description:  TODO 根据key取得Map的值
	 * @param map
	 * @param filed
	 * @return String 
	 */
	@SuppressWarnings("unchecked")
	public static String filed(Map map, String filed) {
		String value = "";
		if (StringUtils.isBlank(filed)) {
			return value;
		}
		if (null == map) {
			return value;
		}
		filed = filed.trim().toUpperCase();
		Object objValue =map.get(filed);
		if (null == objValue) {
			objValue = map.get(filed.toLowerCase());
			if (null == objValue) {
				return value;
			}
		}
		
		
		if (objValue instanceof BigDecimal) {
			BigDecimal bigValue = (BigDecimal) objValue;
			if(bigValue.doubleValue()>bigValue.longValue()){
				value = new Double(bigValue.doubleValue()).toString();
			}else{
				value = new Long(bigValue.longValue()).toString();
			}

			return value;
		}
		if (objValue instanceof Long) {
			Long l = (Long)objValue;
			return l.toString();
		}
        if (objValue instanceof Float) {
            Float l = (Float)objValue;
            return l.toString();
        }
		return StrUtils.null2empty(objValue);
	}
	
	/**
	 * 创建一个UUID
	 * @return
	 */
	public static String ID() {
		return UUID.randomUUID().toString();
	}
	
	
	
	
	
	public String getLogic() {
		return logic;
	}
	public F setLogic(String logic) {
		this.logic = logic;
		return this;
	}
	public String getField() {
		return field;
	}

	public F setField(String field) {
		this.field = field;
		return this;
	}

	public Object getParam() {
		return param;
	}

	public F setParam(Object param) {
		this.param = param;
		return this;
	}

	

	public String getCriteria() {
		return criteria;
	}

	public F setCriteria(String criteria) {
		this.criteria = criteria;
		return this;
	}
	public F[] getF() {
		return f;
	}
	public F setF(F[] f) {
		this.f = f;
		return this;
	}
	public boolean isParams() {
		return isParams;
	}
	public F setParams(boolean isParams) {
		this.isParams = isParams;
		return this;
	}
	
}
