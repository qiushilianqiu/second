package com.gantang.management.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gantang.common.result.Result;
import com.gantang.common.util.Req;
import com.gantang.entity.management.Order;
import com.gantang.entity.management.SysUser;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<Order>{
	/**
	 * 查询
	 * @Title: queryList   
	 * @param order
	 * @return
	 * @throws Exception      
	 * @return: List<GuantTask>      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:35:13
	 */
	List<Order> queryList(Order order)throws Exception;
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
	boolean addBatch(List<Order> list, SysUser user)throws Exception;
	/**
	 * 添加
	 * @Title: add   
	 * @param  order
	 * @return
	 * @throws Exception      
	 * @return: int      
	 * @throws
	 * @author: sl.qiu
	 * @date(创建日期):   2018年12月6日 下午4:36:02
	 */
	int add(Order order)throws Exception;
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
	boolean updateBatch(List<Order> list, SysUser user)throws Exception;
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
	boolean updateBatchById(Order list, SysUser user)throws Exception;

	Page<Order> list(Req<Order> req)throws Exception;

	boolean del(Order order)throws Exception;
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
	 * @param order
	 * @param user 
	 * @return
	 * @throws Exception      
	 * @return: Result      
	 * @throws
	 * @author: sl.qiu
	 */
	Result edit(Order order, SysUser user)throws Exception;
}
