package com.gantang.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * json实体转换工具
 * @author zqs
 *
 */
public class JsonUtil {

	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	
	/**
	 * javabean转json 当有对象需要缓存时先将对象转成json串再进行存储
	 * 
	 * @param bean
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static JSONObject toJSon(Object bean){
		Class<? extends Object> type = bean.getClass();
		JSONObject json = new JSONObject();
		try{
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor p : propertyDescriptor) {
				String propName = p.getName();
				if (!"class".equals(propName)) {
					Method method = p.getReadMethod();
                    Object result = method.invoke(bean);
                    if (null != result) {
						json.put(propName, result);
					} else {
						json.put(propName, "");
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			log.error("转换失败：bean-->" + bean.getClass().getName());
		} 
		return json;
	}
	public static Object toBean(JSONObject json, Class<?> clazz) {
		Object obj = null;
		try{
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			obj = clazz.newInstance();
			PropertyDescriptor[] propertyDescriptor = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor p : propertyDescriptor) {
				String propName = p.getName();
				if (json.containsKey(propName)) {
					Object value = json.get(propName);
					Object[] args = new Object[1];
					args[0] = value;
					p.getWriteMethod().invoke(obj, args);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
			log.error("转换失败：json-->" + json + "  class--->" + clazz.getName());
		}
		return obj;
	}
	/**
	 * 对象转Json字符串
	 */
	public static final String toJSONString(Object object, SerializerFeature... features) {
		//mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		//mapping.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer(dateFormat));
		//mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(dateFormat));
		//JSON.toJSON(javaObject, parserConfig)
		//JSON.DEFFAULT_DATE_FORMAT = dateFormat; 
		//return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat); 

		return JSONObject.toJSONString(object,features);
	}
	/**
	 * 将JSON转换成Collection,其中collectionClz为Collection具体子类的Class,
	 * valueClz为Collection中存放的对象的Class
	 * @Title: json2Collection   
	 * @param jsonString
	 * @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年7月25日 下午3:25:57
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> json2Collection(String jsonString) {
		List<Map<String, Object>> gridData = null;
		if (StringUtils.isBlank(jsonString)) {
			gridData = new ArrayList<Map<String, Object>>();
			return gridData;
		}
		return JSON.parseObject(jsonString, new TypeReference<List<Map<String,Object>>>(){});
	}
}
