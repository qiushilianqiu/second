/**
 * All rights Reserved, Designed By www.GanTang.com.cn
 *
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.GanTang.util
 * @ClassName(类名称):MyBeanUtil
 * @Title(标题): MyBeanUtil.java
 * @see(与该类相关联的类):
 * @author(作者): 深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2018年7月23日 上午10:12:43
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.GanTang.com.cn Inc. All rights reserved.
 * @Description(描述): TODO(用一句话描述该文件做什么)
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业目的
 * ————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * ——————————————————————————————————————
 */
package com.gantang.common.util;

import com.alibaba.fastjson.JSON;
import com.gantang.common.sql.SqlReplace;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


/**
 * ==== All rights Reserved, Designed By www.GanTang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.GanTang.util
 * @ClassName(类名称):MyBeanUtil
 * @Title(标题): MyBeanUtil.java
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2018年7月23日 上午10:12:43
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.GanTang.com.cn Inc. All rights reserved. 
 * @Description(描述): 基础共有类
 * TODO(这里描述这个文件做什么 – 可选)  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目
 * ==== All rights Reserved, Designed By www.GanTang.com.cn ====
 *—————————————————————————————————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人: 
 *    修改原因：
 *
 *—————————————————————————————————————————————————————————————————
 */
public class MyBeanUtil {
    private static Logger log = LoggerFactory.getLogger(MyBeanUtil.class);


    @SuppressWarnings("unchecked")
    public static Map<String, Object> entityToMap(Object bean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null == bean) {
                return map;
            }

            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    //String keyString=SqlReplace.toDbFiled(f.getName()).toUpperCase();
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    if (f.getType().equals(Long.class)) {
                        map.put(f.getName(), f.get(bean));
                    } else if (f.getType().equals(Integer.class)) {
                        map.put(f.getName(), f.get(bean));
                    } else if (f.getType().equals(Float.class)) {
                        map.put(f.getName(), f.get(bean));
                    } else if (f.getType().equals(Double.class)) {
                        map.put(f.getName(), f.get(bean));
                    } else if (f.getType().equals(Date.class)) {
                        if (null != f.get(bean)) {
                            map.put(f.getName(), DateUtil.dispLong((Date) f.get(bean)));
                        } else {
                            map.put(f.getName(), "");
                        }
                    } else {
                        map.put(f.getName(), StrUtils.null2empty(f.get(bean)));
                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     　　* @Description: 实体类转mapList字段名称转换方法,将passWord转成pass_word
     　　* @param tags　　
     ∗ @return{return_type}
     　　* @throws
     　　* @author sl.qiu　　
     ∗  @date 2019/4/4 14:29
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> entityLToMapList(List<?> beans) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        try {
            if (null == beans) {
                return mapList;
            } else {
                for (Object bean : beans) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (null != bean) {
                        Class cls = Class.forName(bean.getClass().getName());
                        for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                            Field[] field = cls.getDeclaredFields();
                            for (Field f : field) {
                                String keyString = SqlReplace.attribute2Field(f.getName()).toUpperCase();
                                if (!f.isAccessible()) {
                                    f.setAccessible(true);
                                }
                                if (f.getType().equals(Long.class)) {
                                    map.put(keyString, f.get(bean));
                                } else if (f.getType().equals(Integer.class)) {
                                    map.put(keyString, f.get(bean));
                                } else if (f.getType().equals(Float.class)) {
                                    map.put(keyString, f.get(bean));
                                } else if (f.getType().equals(Double.class)) {
                                    map.put(keyString, f.get(bean));
                                } else if (f.getType().equals(Date.class)) {
                                    if (null != f.get(bean)) {
                                        map.put(keyString, DateUtil.dispLong((Date) f.get(bean)));
                                    } else {
                                        map.put(keyString, "");
                                    }
                                } else {
                                    map.put(keyString, StrUtils.null2empty(f.get(bean)));
                                }

                            }
                        }
                    }
                    mapList.add(map);
                }
            }


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return mapList;
    }

    public static Map<String, String> entityToMapString(Object bean) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (null == bean) {
                return map;
            }

            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    //String keyString=SqlReplace.toDbFiled(f.getName()).toUpperCase();
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    if (f.get(bean) != null && f.get(bean).toString().length() != 0) {
                        if (f.getType().equals(Long.class)) {
                            map.put(f.getName(), f.get(bean).toString());
                        } else if (f.getType().equals(Integer.class)) {
                            map.put(f.getName(), f.get(bean).toString());
                        } else if (f.getType().equals(Float.class)) {
                            map.put(f.getName(), f.get(bean).toString());
                        } else if (f.getType().equals(Double.class)) {
                            map.put(f.getName(), f.get(bean).toString());
                        } else if (f.getType().equals(Date.class)) {
                            if (null != f.get(bean)) {
                                map.put(f.getName(), DateUtil.dispLong((Date) f.get(bean)));
                            } else {
                                map.put(f.getName(), "");
                            }
                        } else if (f.getType().equals(String.class)) {
                            map.put(f.getName(), StrUtils.null2empty(f.get(bean).toString()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     *
     * @param bean
     * @param fieldFlag true 保持对象属性名称一致，false 数据库字段对应
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> entityToMap(Object bean, Boolean fieldFlag) {
        if (fieldFlag) {
            return MyBeanUtil.entityToMap(bean);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null == bean) {
                return map;
            }

            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String keyString = SqlReplace.attribute2Field(f.getName()).toUpperCase();
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    if (f.getType().equals(Long.class)) {
                        map.put(keyString, f.get(bean));
                    } else if (f.getType().equals(Integer.class)) {
                        map.put(keyString, f.get(bean));
                    } else if (f.getType().equals(Float.class)) {
                        map.put(keyString, f.get(bean));
                    } else if (f.getType().equals(Double.class)) {
                        map.put(keyString, f.get(bean));
                    } else if (f.getType().equals(Date.class)) {
                        if (null != f.get(bean)) {
                            map.put(keyString, DateUtil.dispLong((Date) f.get(bean)));
                        } else {
                            map.put(keyString, "");
                        }
                    } else {
                        map.put(keyString, StrUtils.null2empty(f.get(bean)));
                    }


                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 返回字段不转大写
     * @Title: mapToEntity
     * @param map
     * @param bean
     * @return: void
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年7月25日 上午10:56:39
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    public static void mapToEntityLower(Map<String, String> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String keyString = SqlReplace.attribute2Field(f.getName());
                    String value = map.get(keyString);
                    if (null == value) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }


                    if (f.getType().equals(Long.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(Double.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Double(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (null != value && !StringUtils.isBlank(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            Date date = DateUtil.toDate(value);
                            f.set(bean, date);
                        }
                    } else {
                        f.set(bean, value);

                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"unchecked", "deprecation"})

    public static void mapToEntity(Map<String, String> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            @SuppressWarnings("rawtypes")
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String keyString = SqlReplace.attribute2Field(f.getName()).toUpperCase();
                    String value = map.get(keyString);
                    if (null == value) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }


                    if (f.getType().equals(Long.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(Double.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Double(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (null != value && !StringUtils.isBlank(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            Date date = DateUtil.toDate(value);
                            f.set(bean, date);
                        }
                    } else {
                        f.set(bean, value);

                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    //导入发货单发件人信息专用
    @SuppressWarnings({"unchecked", "deprecation"})
    public static void mapToEntityBySeller(Map<String, String> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String keyString = "SE_" + SqlReplace.attribute2Field(f.getName()).toUpperCase();
                    String value = map.get(keyString);
                    if (null == value) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }


                    if (f.getType().equals(Long.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(Double.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Double(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            Date date = DateUtil.toDate(value);
                            f.set(bean, date);
                        }
                    } else {
                        f.set(bean, value);

                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    //导入订单发件人信息专用
    @SuppressWarnings({"unchecked", "deprecation"})
    public static void mapToEntitySoSender(Map<String, String> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String keyString = "SENDER_" + SqlReplace.attribute2Field(f.getName()).toUpperCase();
                    String value = map.get(keyString);
                    if (null == value) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }


                    if (f.getType().equals(Long.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(Double.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Double(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            Date date = DateUtil.toDate(value);
                            f.set(bean, date);
                        }
                    } else {
                        f.set(bean, value);

                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    //导入订单收件人信息专用
    @SuppressWarnings({"unchecked", "deprecation"})
    public static void mapToEntitySoRec(Map<String, String> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String keyString = "REC_" + SqlReplace.attribute2Field(f.getName()).toUpperCase();
                    String value = map.get(keyString);
                    if (null == value) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }


                    if (f.getType().equals(Long.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(Double.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new Double(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (!StringUtils.isEmpty(value)) {
                            Date date = DateUtil.toDate(value);
                            f.set(bean, date);
                        }
                    } else {
                        f.set(bean, value);

                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /*暂不使用*/
//	public static String contrastyEntity(Object newEntity,Object oldEntity) {
//		String newClassName = newEntity.getClass().getName();
//		String oldClassName = oldEntity.getClass().getName();
//		if(!newClassName.equals(oldClassName)) {
//			throw new GanTangVerifyException("两个对象不一致");
//		}
//		StringBuilder sBuilder = new StringBuilder();
//		StringBuilder senBuilder = new StringBuilder();
//		try {
//			
//			Class cls = Class.forName(newEntity.getClass().getName());
//			for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
//				Field[] field = cls.getDeclaredFields();
//				for (Field f : field) {
//				
//				
//					if (!f.isAccessible()) {
//						f.setAccessible(true);
//					}
//					ColumnAttribute columnAttribute	=f.getAnnotation(ColumnAttribute.class);
//					//没有属性比对注解的 
//					if(null == columnAttribute) {
//						continue;
//					}
//					//不需要属性对比
//					if(!columnAttribute.check()) {
//						continue;
//					}
//					String ncn = columnAttribute.ncn();
//					String nen = columnAttribute.nen();
//					String attributeName = f.getName();
//					StringBuilder fieldNcn = new StringBuilder();
//					StringBuilder fieldNen = new StringBuilder();
//					if(StringUtils.isBlank(ncn) && StringUtils.isBlank(nen)) {
//						fieldNcn.append("[").append(attributeName).append("]");
//						fieldNen.append("[").append(attributeName).append("]");
//					}else if(StringUtils.isNotBlank(ncn)&&StringUtils.isNotBlank(nen)) {
//						fieldNcn.append("[").append(ncn).append("]");
//						fieldNen.append("[").append(nen).append("]");
//					}else if(StringUtils.isBlank(ncn)&&StringUtils.isNotBlank(nen)) {
//						fieldNcn.append("[").append(nen).append("]");
//						fieldNen.append("[").append(nen).append("]");
//					}else if(StringUtils.isNotBlank(ncn)&&StringUtils.isBlank(nen)) {
//						fieldNcn.append("[").append(ncn).append("]");
//						fieldNen.append("[").append(ncn).append("]");
//					}
//					
//					
//					
//					Object newFieldValue = f.get(newEntity);
//					
//					Object oldFieldValue = getFieldValue(oldEntity,attributeName);
//					if(null==newFieldValue || oldFieldValue==null) {
//						if(null==newFieldValue 
//								&& oldFieldValue!=null && StringUtils.isNotBlank(oldFieldValue.toString())) {
//							sBuilder.append(";").append(fieldNcn).append("由[").append(oldFieldValue).append("]修改成").append("空");	
//							senBuilder.append(";").append(fieldNen).append(" is changed  from [").append(oldFieldValue).append("]to").append(" empty");	
//						}else if(null!=newFieldValue && StringUtils.isNotBlank(newFieldValue.toString())   && oldFieldValue==null) {
//							sBuilder.append(";").append(fieldNcn).append("由空修改成").append("[").append(newFieldValue).append("]");	
//							senBuilder.append(";").append(fieldNen).append(" is changed from empty to").append("[").append(newFieldValue).append("]");	
//		
//						}
//						
//					}else {
//						if(!newFieldValue.equals(oldFieldValue) 
//								&& StringUtils.isNotBlank(newFieldValue.toString())
//								 && StringUtils.isNotBlank(oldFieldValue.toString())) {
//							sBuilder.append(";").append(fieldNcn).append("由[").append(oldFieldValue).append("]修改成[").append(newFieldValue).append("]");	
//							senBuilder.append(";").append(fieldNen).append(" is changed  from [").append(oldFieldValue).append("]to[").append(newFieldValue).append("]");
//			
//						}
//					}
//					
//					
//					
//				}
//				
//			}
//			
//		} catch (Exception e) {
//			
//			log.error(e.getMessage(), e);
//			e.printStackTrace();
//			throw new GanTangVerifyException(e);
//		}
//
//		String result = sBuilder.append(senBuilder).toString();
//		return result.startsWith(";")?result.replaceFirst(";", ""):result;
//	}
    @SuppressWarnings("unchecked")
    public static void mapObjectToEntityLower(Map<String, Object> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String fieldName = SqlReplace.attribute2Field(f.getName());
                    if (null == map.get(fieldName)) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    String value = "";
                    if (map.get(fieldName) instanceof BigDecimal) {

                        String strValue = map.get(fieldName).toString();

                        if (strValue.indexOf(".") == -1) {
                            value = map.get(fieldName).toString();
                        } else {
                            BigDecimal bigValue = (BigDecimal) map
                                    .get(fieldName);
                            value = new Double(bigValue.doubleValue())
                                    .toString();
                        }

                    } else if (map.get(fieldName) instanceof Timestamp) {
                        Timestamp time = (Timestamp) map.get(fieldName);
                        if (null != time) {
                            Date d = new Date(time.getTime());
                            value = DateUtil.dispLong(d);
                        }

                    } else if (map.get(fieldName) instanceof Date) {
                        Date date = (Date) map.get(fieldName);
                        if (null != date) {

                            value = DateUtil.dispLong(date);
                        }

                    } else {
                        value = F.filed(map, fieldName);
                    }
                    if ("null".equals(value) || "NULL".equals(value)) {
                        value = "";
                    }
                    if (f.getType().equals(Long.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, DateUtil.toDate(value));
                        }
                    } else if (f.getType().equals(Boolean.class) || f.getType().equals(boolean.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Boolean(value));
                        }
                    } else {
                        f.set(bean, value);
                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    /**
     *
     * 将dto和entity之间的属性互相转换,dto中属性一般为String等基本类型,
     *
     * 但是entity中可能有复合主键等复杂类型,需要注意同名问题
     *
     *
     */
    public static Object populate(Object dto, Object entity) {

        Method[] dtoMethods = dto.getClass().getMethods();

        Method[] entityMethods = entity.getClass().getMethods();

        for (Method m : dtoMethods) {

            String dtoName = m.getName();

            if (dtoName.startsWith("get")) {

                try {

                    Object result = m.invoke(dto);

                    for (Method mm : entityMethods) {

                        String entityName = mm.getName();

                        if (entityName.startsWith("set")
                                && entityName.substring(3, entityName.length())

                                .equals(dtoName.substring(3, dtoName.length()))) {

                            mm.invoke(entity, result);

                        }

                    }

                } catch (Exception e) {

                }

            }

        }

        return entity;

    }

    @SuppressWarnings("unchecked")
    public static void mapObjectToEntity(Map<String, Object> map, Object bean) {
        try {
            if (null == map) {
                return;
            }
            if (map.size() <= 0) {
                return;
            }
            Class cls = Class.forName(bean.getClass().getName());
            for (; !cls.equals(Object.class); cls = cls.getSuperclass()) {
                Field[] field = cls.getDeclaredFields();
                for (Field f : field) {
                    String fieldName = SqlReplace.attribute2Field(f.getName())
                            .toUpperCase();
                    if (null == map.get(fieldName)) {
                        continue;
                    }

                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }
                    String value = "";
                    if (map.get(fieldName) instanceof BigDecimal) {

                        String strValue = map.get(fieldName).toString();

                        if (strValue.indexOf(".") == -1) {
                            value = map.get(fieldName).toString();
                        } else {
                            BigDecimal bigValue = (BigDecimal) map
                                    .get(fieldName);
                            value = new Double(bigValue.doubleValue())
                                    .toString();
                        }

                    } else if (map.get(fieldName) instanceof Timestamp) {
                        Timestamp time = (Timestamp) map.get(fieldName);
                        if (null != time) {
                            Date d = new Date(time.getTime());
                            value = DateUtil.dispLong(d);
                        }

                    } else if (map.get(fieldName) instanceof Date) {
                        Date date = (Date) map.get(fieldName);
                        if (null != date) {

                            value = DateUtil.dispLong(date);
                        }

                    } else {
                        value = F.filed(map, fieldName);
                    }
                    if ("null".equals(value) || "NULL".equals(value)) {
                        value = "";
                    }
                    if (f.getType().equals(Long.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Long(value));
                        }
                    } else if (f.getType().equals(Float.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Float(value));
                        }
                    } else if (f.getType().equals(BigDecimal.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new BigDecimal(value));
                        }
                    } else if (f.getType().equals(Integer.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Integer(value));
                        }
                    } else if (f.getType().equals(Date.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, DateUtil.toDate(value));
                        }
                    } else if (f.getType().equals(Boolean.class) || f.getType().equals(boolean.class)) {
                        if (null != value && StringUtils.isNotBlank(value)) {
                            f.set(bean, new Boolean(value));
                        }
                    } else {
                        f.set(bean, value);
                    }

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void mapObjectToMapString(Map<String, Object> mapObject, Map<String, String> mapString) {

        if (null == mapObject) {
            return;
        }
        if (mapObject.size() <= 0) {
            return;
        }

        for (String key : mapObject.keySet()) {
            String value = "";
            if (mapObject.get(key) instanceof BigDecimal) {
                String strValue = mapObject.get(key).toString();

                if (strValue.indexOf(".") == -1) {
                    value = mapObject.get(key).toString();
                } else {
                    BigDecimal bigValue = (BigDecimal) mapObject.get(key);
                    value = new Double(bigValue.doubleValue())
                            .toString();
                }
            } else {
                value = String.valueOf(mapObject.get(key));
            }
            mapString.put(key, value);
        }
    }


    public static void mapStringToMapObject(Map<String, String> mapString, Map<String, Object> mapObject) {

        if (null == mapString) {
            return;
        }
        if (mapString.size() <= 0) {
            return;
        }
        for (String key : mapString.keySet()) {
            mapObject.put(key, mapString.get(key));
        }
    }

    public static void mapObjectToMapString(List<Map<String, Object>> mapObjectList, List<Map<String, String>> mapStringList) {
        if (mapObjectList == null || mapStringList == null) {
            return;
        }
        if (mapObjectList.size() <= 0) {
            return;
        }
        for (Map<String, Object> mapObject : mapObjectList) {
            Map<String, String> mapString = new HashMap<String, String>();
            mapObjectToMapString(mapObject, mapString);
            if (mapString.size() > 0) {
                mapStringList.add(mapString);
            }
        }
    }

    public static <T> List<T> mapListToBeanList(
            List<Map<String, Object>> mapObjectList, Class<T> entityClass) {
        List<T> dataList = new ArrayList<T>();
        for (Map<String, Object> map : mapObjectList) {
            T item;
            try {
                item = entityClass.newInstance();
                MyBeanUtil.mapObjectToEntity(map, item);
                dataList.add(item);
            } catch (Exception e) {
                throw new RuntimeException(entityClass + "实例化失败");
            }
        }
        return dataList;
    }


    public static void mapStringToMapObject(List<Map<String, String>> mapStringList, List<Map<String, Object>> mapObjectList) {
        if (mapStringList == null || mapStringList == null) {
            return;
        }
        if (mapStringList.size() <= 0) {
            return;
        }

        for (Map<String, String> mapString : mapStringList) {
            Map<String, Object> mapObject = new HashMap<String, Object>();
            mapStringToMapObject(mapString, mapObject);
            if (mapString.size() > 0) {
                mapObjectList.add(mapObject);
            }
        }
    }

    /**
     　　* @Description: Object转map
     　　* @param tags　　
     ∗ @return{return_type}
     　　* @throws
     　　* @author sl.qiu　　
     ∗  @date 2019/3/6 16:35
     */
    public Map<String, Object> Obj2Map(Object obj) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     　　* @Description: map转object
     　　* @param tags　　
     ∗ @return{return_type}
     　　* @throws
     　　* @author sl.qiu　　
     ∗  @date 2019/3/6 16:35
     */
    public Object map2Obj(Map<String, Object> map, Class<?> clz) throws Exception {
        Object obj = clz.newInstance();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    /**
     　　* @Description: objToListMap转
     　　* @param tags　　
     ∗ @return{return_type}
     　　* @throws
     　　* @author sl.qiu　　
     ∗  @date 2019/3/6 16:42
     */
    public static List<Map<String, Object>> objToListMap(String json) {
        List<Object> list = JSON.parseArray(json);

        List<Map<String, Object>> listw = new ArrayList<Map<String, Object>>();
        for (Object object : list) {
            Map<String, Object> ageMap = new HashMap<String, Object>();
            Map<String, Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
        /*for (Entry<String, Object> entry : ret.entrySet()) {
            ageMap.put(entry.getKey());
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            listw.add(ageMap);  //添加到list集合  成为 list<map<String,Object>> 集合
        }  */
            listw.add(ret);
        }
        return listw;

    }
    /**
     * 获得同时有get和set的field和value。
     *
     * @param bean
     * @return
     */
/*	@SuppressWarnings("unchecked")
    public static Map<String,Object> describe(Object bean) {
		Map<String,Object> des = new HashMap<String,Object>();
		PropertyDescriptor desor[] = PropertyUtils.getPropertyDescriptors(bean);
		
		
		String name = null;
		for (int i = 0; i < desor.length; i++) {
			if (desor[i].getReadMethod() != null
					&& desor[i].getWriteMethod() != null) {
				name = desor[i].getName();
				try {
					des.put(name, PropertyUtils.getProperty(bean, name));
				} catch (Exception e) {
					throw new RuntimeException("属性不存在：" + name);
				}
			}
		}
		return des;
	}*/

	public static void setSimpleProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setSimpleProperty(bean, name, value);
		} catch (Exception e) {
			throw new RuntimeException("属性不存在：" + name);
		}
	}

	public static Object setSimpleProperty(Object bean, String name) {
		try {
			return PropertyUtils.getSimpleProperty(bean, name);
		} catch (Exception e) {
			throw new RuntimeException("属性不存在：" + name);
		}
	}

    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
/*	public static Object getFieldValue(Object object, String fieldName)
			throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常{}"+ e.getMessage(),e);
		}
		return result;
	}

	
	public static Object getAnnotation(Object object, String fieldName,Class cla)
			throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		
		return field.getAnnotation(cla);
	}*/
    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
/*	public static void setFieldValue(Object object, String fieldName,
			Object value) throws NoSuchFieldException {
		Field field = getDeclaredField(object, fieldName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			log.error("不可能抛出的异常:{}"+ e.getMessage(), e);
		}
	}*/

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
/*	public static Field getDeclaredField(Object object, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		return getDeclaredField(object.getClass(), fieldName);
	}

	*/
    /**
     * 循环向上转型,获取类的DeclaredField.
     *//*
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(Class clazz, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such filed: " + clazz.getName()
				+ '.' + fieldName);
	}*/
}
