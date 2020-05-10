package com.gantang.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

    List<Map<String, Object>> getDictionList(@Param("map") Map<String, String> map);

    List<Map<String, Object>> getEveryTableList(@Param("map") Map<String, String> map);
}
