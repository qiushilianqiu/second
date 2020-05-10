package com.gantang.management.mapper;



import java.util.List;
import java.util.Map;
import com.gantang.entity.management.SysUser;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>{
	/**
	 * 查询
	 * @Title: getList   
	 * @param map
	 * @return      
	 * @return: List<Map<String,Object>>      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:48:29
	 */
	List<Map<String, Object>> getList(Map<String, String> map);
	/**
     * 编辑
     * @Description: 
     * @param @param record
     * @param @return   
     * @return int  
     * @author sl.qiu
     * @date 2018年6月26日下午3:26:23
     */
    int updateByPrimaryKeySelective(SysUser record);
}
