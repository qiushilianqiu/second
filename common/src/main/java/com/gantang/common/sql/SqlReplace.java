package com.gantang.common.sql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SqlReplace {

	/**
	 * @Description:  TODO SQL语句替换Form的方法，将书写不标准的form替换成统一的FROM
     * @author:仇石廉
	 * @param sql SQL语句
	 * @param from 替换成的字符串
	 * @return String 新的SQL语句
	 */
	public String replace(String sql, String from) {
		Pattern p = Pattern.compile("[Ff][Rr][Oo][Mm]");
		Matcher m = p.matcher(sql);
		sql = m.replaceAll(from);
		return sql;
	}
	 /**
     * 
     * @Title: getTableName   
     * @Description: TODO 根据实体类获取表格名称
     * @param entityClass 
     * @return: String      
     * @throws
     */
//    public static String getTableName(Class entityClass){
//    	Table table = (Table) entityClass.getAnnotation(Table.class);
//    	if(null == table) {
//    		throw new PwRuntimeException(entityClass+"没有table的注解");
//    	}
//    	return table.name();
//    }
	/**
	 * @Description:  TODO 字段名称转换方法,将passWord转成pass_word
     * @author:仇石廉
	 * @param filed  字段名称
	 * @return String
	 */
	public static String attribute2Field(String filed) {
		String regEx = "([A-Z])"; // 表示一个或多个
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(filed);
		while (m.find()) {
			filed = filed.replace(m.group(1), "_" + m.group(1).toLowerCase());
		}
		if (filed.startsWith("_")) {
			filed = filed.substring(1);
		}
		return filed;
	}

	/**
	 * 
	 * @Title: filed2Attribute
     * @author:仇石廉
	 * @Description: TODO 将实体类属性名称转为字段名称 te_tt to TeTt
	 * @param  filed
	 * @return String
	 * @throws
	 */
	public static String filed2Attribute(String filed,boolean upperFirst) {
		String[] f=filed.split("_");
		StringBuffer sb=new StringBuffer();
		if(null!=f&&f.length>0){
			for(int i=0,len=f.length;i<len;i++){
				if(i==0 && !upperFirst) {
				   sb.append(f[i].toLowerCase().substring(0,1).trim())
				   .append(f[i].toLowerCase().substring(1).trim());
				}else {
					sb.append(f[i].toUpperCase().substring(0,1).trim())
					.append(f[i].toLowerCase().substring(1).trim());	
				}
			}
		}
		
		return sb.toString();
	}
	
	
	/**
	 * @Title: toClassName   
	 * @Description: TODO 将表格名称转为实体类名称
	 * @param entityClass   
	 * @return: String      
	 * @throws
	 * @author: sl.qiu
	 */
	public static String toClassName(Class entityClass){
		String tableName = entityClass.getName().substring(entityClass.getPackage().getName().length()+1);
		return tableName;
	}
	/**
	 * @Title: toTableName   
	 * @Description:  TODO 将实体类名称转为表名
	 * @param entityClass     
	 * @return: String      
	 * @throws
	 * @author: 仇石廉  sl.qiu
	 */
	/*public static String toTableName(Class entityClass){
		return getTableName(entityClass);
	}*/
	
	
	
	public static void main(String[] arge) {

	}

}
