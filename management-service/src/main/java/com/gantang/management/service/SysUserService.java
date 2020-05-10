package com.gantang.management.service;


import java.util.List;
import java.util.Map;
import com.gantang.entity.management.SysUser;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gantang.common.result.Result;
import com.gantang.common.util.Req;
import com.gantang.entity.management.SysUser;

public interface SysUserService extends IService<SysUser>{
	/**
	 * 查询
	 * @Title: queryList   
	 * @param sysUserDto
	 * @return
	 * @throws Exception      
	 * @return: List<GuantTask>      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:35:13
	 */
	List<SysUser> queryList(SysUser sysUser)throws Exception;
	/**
	 * 批量插入
	 * @Title: addBatch   
	 * @param list
	 * @return
	 * @throws Exception      
	 * @return: boolean      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:35:33
	 */
	boolean addBatch(List<SysUser> list, SysUser user)throws Exception;
	/**
	 * 添加
	 * @Title: add   
	 * @param  sysUser
	 * @return
	 * @throws Exception      
	 * @return: int      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:36:02
	 */
	int add(SysUser sysUser)throws Exception;
	/**
	 * 批量更新
	 * @Title: updateBatch   
	 * @param list
	 * @return
	 * @throws Exception      
	 * @return: boolean      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:36:23
	 */
	boolean updateBatch(List<SysUser> list, SysUser user)throws Exception;
		/**
	 * 单条跟新
	 * @Title: updateBatch   
	 * @param list
	 * @return
	 * @throws Exception      
	 * @return: boolean      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:36:23
	 */
	boolean updateBatchById(SysUser list, SysUser user)throws Exception;

	Page<SysUser> list(Req<SysUser> req)throws Exception;

	boolean del(SysUser sysUser)throws Exception;
	/**查询列表   
	 * @Title: getList   
	 * @param map
	 * @return      
	 * @return: String      
	 * @throws
	 */  
	List<Map<String, Object>> getList(Map<String, String> map) throws Exception;
	/**
	 * 添加
	 * @Title: edit   
	 * @param sysUser
	 * @param user 
	 * @return
	 * @throws Exception      
	 * @return: Result      
	 * @throws
	 * @author: sl.qiu
	 */
	Result edit(SysUser sysUser, SysUser user)throws Exception;
}
