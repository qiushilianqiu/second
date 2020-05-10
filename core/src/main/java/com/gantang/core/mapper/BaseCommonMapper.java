package com.gantang.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BaseCommonMapper {

    /**
     * TODO:map
     *
     * @param map
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.String>>
     * @author sl.qiu
     * @date 2020/4/23 15:20
     */
    List<Map<String, String>> getdictionlist(Map<String, String> map);

    /**
     * 查询任意表信息的任意字段
     *
     * @author sl.qiu
     * @date 2020/4/23 14:59
     */
    List<Map<String, String>> getList(@Param("map") Map<String, String> map);

    /**
     * 查询任意表信息的所有字段
     *
     * @author sl.qiu
     * @date 2020/4/23 14:59
     */
    List<Map<String, Object>> select(@Param("list") List<Object> list, @Param("table") String table);

    /**
     * 根据条件查询单条记录
     *
     * @author sl.qiu
     * @date 2020/4/23 15:00
     */
    Map<String, Object> selectByWhere(@Param("list") List<Object> list, @Param("table") String table);

    /**
     * 根据Id删除
     *
     * @author sl.qiu
     * @date 2020/4/23 15:01
     */
    int deleteById(@Param("table") String table, @Param("id") long id);

    /**
     * 条件删除
     *
     * @author sl.qiu
     * @date 2020/4/23 15:01
     */
    int deleteByWhere(@Param("table") String table, @Param("deleteList") List<Object> deleteList);

    /**
     * 根据id查询
     *
     * @author sl.qiu
     * @date 2020/4/23 15:01
     */
    Map<String, Object> selectById(@Param("table") String table, @Param("id") long id);

    /**
     * 插入
     *
     * @author sl.qiu
     * @date 2020/4/23 15:01
     */
    int save(@Param("insertMap") Map<String, String> insertMap, @Param("table") String table);

    /**
     * 插入
     *
     * @author sl.qiu
     * @date 2020/4/23 15:01
     */
    int saveObject(@Param("insertMap") Map<String, Object> insertMap, @Param("table") String table);

    /**
     * 修改业务数据
     */
    int updateList(@Param("table") String table, @Param("list") List<Object> list, @Param("conditionStr") String conditionStr);

    /**
     * 修改业务数据
     */
    int update(@Param("table") String table, @Param("list") List<Object> list, @Param("conditionStr") String conditionStr);

    /**
     * 更新所有字段
     */
    int updateAll(@Param("table") String table, @Param("list") List<Object> list);

    /**
     * 条件跟新
     */
    int updateByWhere(@Param("table") String table, @Param("updateList") List<Object> updateList, @Param("whereList") List<Object> whereList);

}
