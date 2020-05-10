package com.gantang.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util
 * @ClassName(类名称):CollectionUtil
 * @Title(标题):  CollectionUtil.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月20日 下午5:42:01   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  工具类 
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
public class CollectionUtil {
   /**
    * 将List 分为多个List
    * @param list  需要分开的List
    * @param batchCount 设置每个List的大小
    * @return List<List<String>>
    */
	public static List<List<String>> splitStr(List<String> list,
			int batchCount) {
		
		if (isNullList(list)) {
			return null;
		}
		List<List<String>> listAll = new ArrayList<List<String>>();
		List<String> listTemp = new ArrayList<String>();
		int i = 0;
		for (String fCode : list) {
			if(StringUtils.isNotBlank(fCode)){
			  listTemp.add(fCode);
			}
			i++;
			if (i == batchCount) {
				listAll.add(listTemp);
				listTemp = new ArrayList<String>();
				i = 0;
			}
		}
		if (isNotNullList(listTemp)) {
			listAll.add(listTemp);
		}
		return listAll;
	}
	
	/**
	    * 将List 分为多个List
	    * @param list  需要分开的List
	    * @param batchCount 设置每个List的大小
	    * @return List<List<String>>
	    */
		public static List<List<Long>> splitLong(List<Long> list,
				int batchCount) {
			
			if (isNullList(list)) {
				return null;
			}
			List<List<Long>> listAll = new ArrayList<List<Long>>();
			List<Long> listTemp = new ArrayList<Long>();
			int i = 0;
			for (Long fCode : list) {
				if(null != fCode){
				  listTemp.add(fCode);
				}
				i++;
				if (i == batchCount) {
					listAll.add(listTemp);
					listTemp = new ArrayList<Long>();
					i = 0;
				}
			}
			if (isNotNullList(listTemp)) {
				listAll.add(listTemp);
			}
			return listAll;
		}
		
	
	public static List<List<?>> splitObject(List<?> list,
			int batchCount) {
		
		if (isNullList(list)) {
			return null;
		}
		List<List<?>> listAll = new ArrayList<List<?>>();
		List listTemp = new ArrayList();
		int i = 0;
		for (Object item : list) {
			if(null!= item){
			  listTemp.add(item);
			}
			i++;
			if (i == batchCount) {
				listAll.add(listTemp);
				listTemp = new ArrayList();
				i = 0;
			}
		}
		if (isNotNullList(listTemp)) {
			listAll.add(listTemp);
		}
		return listAll;
	}
	/**
	    * 将List 分为多个List
	    * @param list  需要分开的List
	    * @param batchCount 设置每个List的大小
	    * @return List<List<String>>
	    */
	public static List<List<Object[]>> splitList(List<Object[]> list,
			int batchCount) {
		
		if (isNullList(list)) {
			return null;
		}
		List<List<Object[]>> listAll = new ArrayList<List<Object[]>>();
		List<Object[]> listTemp = new ArrayList<Object[]>();
		int i = 0;
		for (Object[] fCode : list) {
			listTemp.add(fCode);
			i++;
			if (i == batchCount) {
				listAll.add(listTemp);
				listTemp = new ArrayList<Object[]>();
				i = 0;
			}
		}
		if (isNotNullList(listTemp)) {
			listAll.add(listTemp);
		}
		return listAll;
	}
	
	/**
	    * 将List 分为多个List
	    * @param list  需要分开的List
	    * @param batchCount 设置每个List的大小
	    * @return List<List<String>>
	    */

	public static List<List<Map<String, Object>>> splitMap(List<Map<String, Object>> list,
			int batchCount) {
		 if (isNullList(list)) {
			return null;
		 }
		 List<List<Map<String, Object>>>  listAll= new ArrayList<List<Map<String, Object>>>();
		 List<Map<String, Object>> listTemp = new ArrayList<Map<String, Object>>();
		 int i = 0;
		 for (Map<String, Object> item : list) {
			listTemp.add(item);
			i++;
			if (i == batchCount) {
				listAll.add(listTemp);
				listTemp = new ArrayList<Map<String, Object>>();
				i = 0;
			}
		}
		if (isNotNullList(listTemp)) {
			listAll.add(listTemp);
		}
		return listAll;
	}
	
	/**
	 * 判断List是否空，空返回True,非空返回false
	 * @param list
	 * @return
	 */
	public static boolean isNullList(List<?> list) {

        return null == list || list.size() <= 0;
    }
	/**
	 * 判断List是否不为空，非空返回True,空返回false
	 * @param list
	 * @return
	 */
	public static boolean isNotNullList(List<?> list) {

        return null != list && list.size() > 0;
    }
   /**
    * 根据List生成SQL语句中in ()内的字符串
    * @param list
    * @return
    */
	public static String buildSQL(List<String> list){
		if(isNotNullList(list)){
			StringBuilder sb = new StringBuilder();
			for(String str:list){
				if(StringUtils.isNotEmpty(str)){
				str = str.replace("'", "''");
				str = str.replace("and", "");
				str = str.replace("or", "");
				str = str.replace(" ", "");
				sb.append(",'").append(str.trim()).append("'");
				}
			}
			if(sb.toString().length()>=1){
				return sb.toString().substring(1);
			}else{
				return "";
			}
		}else{
			return "";
		}
	}



	/**
	 * 判断Map是否空，空返回True,非空返回false
	 * @param map
	 * @return
	 */
	public static boolean isNullMap(Map<?,?> map) {

        return null == map || map.size() <= 0;
    }
	/**
	 * 判断map是否不为空，非空返回True,空返回false
	 * @param map
	 * @return
	 */
	public static boolean isNotNullMap(Map<?,?> map) {

        return null != map && map.size() > 0;
    }
	
	
	
	@SuppressWarnings("unchecked")
	public static String db(Map map, String key) {
		String value = "";
		if (null == map) {
			return value;
		}
		if (null == map.get(key)) {
			return value;
		}

		if (map.get(key) instanceof BigDecimal) {
			BigDecimal bigValue = (BigDecimal) map.get(key);
			value = new Long(bigValue.longValue()).toString();
			return value;
		}
		if (map.get(key) instanceof Long) {
			Long l = (Long) map.get(key);
			return l.toString();
		}

		return map.get(key).toString();

	}
}
