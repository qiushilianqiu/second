package com.gantang.core.service;


import com.gantang.common.util.F;

import java.util.List;
import java.util.Map;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):Baseservice
 * @Package(包名称) Baseservice.
 * @ClassName(类名称):BaseService
 * @Title(标题):     
 * @see(与该类相关联的类):  
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/6/14 11:00   
 * @version(版本): V1.0 
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    (用一句话描述该文件做什么)  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业目的
 *————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人: 
 *    修改原因：
 *              
 *——————————————————————————————————————
 */
public interface BaseService {

    /**根据条件查询所有的字段值
     * @Title: load
     * @param tableName
     * @param f
     * @return
     * @return: List<Map<String,Object>>
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年7月24日 上午11:17:43
     */
    List<Map<String, Object>> load(String tableName, F[] f);

    /**
     * 根据条件查出单条记录
     * @Title: loadMap
     * @param tableName
     * @param f
     * @return
     * @return: Map<String,Object>
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年7月24日 上午11:37:14
     */
    Map<String, String> loadMap(String tableName, F[] f);

    /**
     * 根据ID查询单条记录
     * @Title: loadMap
     * @param tableName
     * @param f
     * @return
     * @return: Map<String,Object>
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年7月24日 上午11:37:14
     */
    Map<String, String> loadMap(String tableName, F f);
    /*    *//**
     * 根据条件查询对应多个字段的字段值
     * @Title: loadMap
     * @param tableName
     * @param column
     * @param fieldParam
     * @return
     * @return: Map<String,Object>
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年7月24日 上午11:45:25
     *//*
    public Map<String, Object> loadMap(String tableName,String[] column,F... fieldParam);
    *//**
     * 根据条件查询对应单个字段的字段值
     * @Title: loadMap
     * @param tableName
     * @param column
     * @param fieldParam
     * @return
     * @return: Map<String,Object>
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年7月24日 上午11:45:25
     *//*
    public Map<String, Object> loadMap(String tableName,String column,F... fieldParam);*/

//	/**
//	 * @Description: TODO 原生的SQL查找方法（不带参数）
//	 * @author
//	 * @param sql  SQL语句
//	 * @return Map<String, Object> 返回Map<String, Object>
//	 */
//	public Map<String, Object> loadMap(String sql);
    /**添加表
     * @Description: TODO 添加方法</br> 例子: </br>
     * insert("emp",new FieldParam[]{new FieldParam("id",1),new FieldParam("name","cpk")});</br>
     * 往emp表添加一条id=1,name=cpk的数据</br>
     * @author
     * @param table   表名称
     * @param fieldParam
     * @return int 返回影响数据库的行数
     * @throws
     */
    int insert(String table, F... fieldParam);

    /*	*//**
     * 批量添加
     * @Title: batchInsert
     * @param entityList
     * @return
     * @return: int
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年7月24日 上午11:48:07
     *//*
    public int batchInsert(List<?> entityList);*/

    /**更新所有该表所有数据
     * @Description: TODO 更新方法
     *
     * @author
     * @param tableName
     *            数据库表名
     * @param fieldParam
     *            更新fieldParam对象数组
     * @return int 返回更新的行数
     */
    int updateByFieldParam(String tableName, F... fieldParam);

    /**更新方法
     * @Description: TODO 更新方法
     *
     * @author
     * @param tableName
     *            数据库表名
     * @param fieldParamUpdate
     *            更新fieldParam对象数组
     * @param fieldParamWhere
     *            更新条件fieldParam对象数组
     * @return int 返回更新的行数
     */
    int updateByFieldParam(String tableName, F[] fieldParamUpdate,
                           F[] fieldParamWhere);

    /**删除方法
     * @Description: TODO 删除方法
     *
     * @author
     * @param tableName
     *            数据库表名
     * @param fieldParamWhere
     *            删除条件fieldParam对象数组
     * @return int 返回更新的行数
     */
    int delete(String tableName, F[] fieldParamWhere);

    /**删除方法
     * @Description: TODO 删除方法
     *
     * @author
     * @param tableName
     *            数据库表名
     *            删除条件fieldParam对象数组
     * @return int 返回更新的行数
     */
    int delete(String tableName, F f);

    /*	*//**
     * @Description: TODO 原生的SQL查找方法
     *
     * @author
     * @param sql
     *            SQL语句
     * @param values
     *            参数对象数组
     * @return List<Map<String, Object>> 返回List<Map<String, Object>>
     *//*
	public List<Map<String, Object>> queryMap(String sql, Object... values);

	*//**
     * @Description: TODO 原生的SQL查找方法
     *
     * @author
     * @param tableName   数据库表名
     * @param fieldParam  查询条件fieldParam对象数组
     * @return List<Map<String, Object>> 返回List<Map<String, Object>>
     *//*

	public List<Map<String, Object>> queryMap(String tableName, F... fieldParam);


    *//**
     *
     * @Description: TODO 调用存储过程
     * @param  sql 调用语句 如 call testpro('p1','p2')
     *
     *//*
    public void executeCall(String sql);*/




}
