package com.gantang.management.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gantang.common.annotation.SysLog;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultCode;
import com.gantang.common.util.DateUtil;
import com.gantang.common.util.Req;
import com.gantang.entity.management.Order;
import com.gantang.entity.management.SysUser;
import com.gantang.management.mapper.OrderMapper;
import com.gantang.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):InnerApiService
 * @Package(包名称) com.gantang.service.impl
 * @ClassName(类名称):OrderServiceImpl
 * @Title(标题):  OrderServiceImpl.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团 sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2020-04-30 10:05:00
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):   
 * TODO(这里描述这个文件做什么 – 可选)  
 * 注意：本内容仅限于甘棠餐饮集团内部传阅，禁止外泄以及用于其他的商业项目
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
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{
	@Autowired
	OrderMapper orderMapper;
	/**
	 * 无分页的查询
	 * <p>Title: queryList</p>   
	 * <p>Description: </p>   
	 * @param order
	 * @return
	 * @throws Exception   
	 */
	 
	@Override
	public List<Order> queryList(Order order) {
		EntityWrapper<Order> wrapper = new EntityWrapper<>();

					order.setId(order.getId());
					order.setCode(order.getCode());
					order.setType(order.getType());
					order.setModifier(order.getModifier());
					order.setModifyTime(order.getModifyTime());
					order.setCreater(order.getCreater());
					order.setCreateTime(order.getCreateTime());
				wrapper.setEntity(order);
		List<Order> selectList = this.selectList(wrapper);
		return selectList;
	}
	/**
	 * 批量增
	 * <p>Title: addBatch</p>   
	 * <p>Description: </p>   
	 * @param list
	 * @return
	 * @throws Exception   
	 */
	@SysLog("批量增加")
	@Override
	public boolean addBatch(List<Order> list,SysUser user) {
		List<Order> uList = new ArrayList<>();
		for (Order order : list) {
            order.setModifier(user.getId());
			order.setModifyTime(DateUtil.getStringDate());
			order.setCreater(user.getId());
			order.setCreateTime(DateUtil.getStringDate());
			uList.add(order);
		}
		boolean insertBatch = this.insertBatch(uList);
		return insertBatch;
	}

	/**
	 * 单增
	 * <p>Title: add</p>   
	 * <p>Description: </p>   
	 * @param order
	 * @return
	 * @throws Exception   
	 */
	@Override
	public int add(Order order) {

		boolean insert = this.insert(order);
		int id=order.getId();
		return id;
	}
		/**
	 * 单条跟新
	 * <p>Title: updateBatch</p>   
	 * <p>Description: </p>   
	 * @param order
	 * @return
	 * @throws Exception   
	 */
	@Override
	public boolean updateBatchById(Order order,SysUser user) {

			order.setModifier(user.getId());
			order.setModifyTime(DateUtil.getStringDate());
			boolean updateBatchById = this.updateById(order);
		return updateBatchById;
	}
	/**
	 * 批量更
	 * <p>Title: updateBatch</p>   
	 * <p>Description: </p>   
	 * @param list
	 * @return
	 * @throws Exception   
	 */
	@Override
	public boolean updateBatch(List<Order> list,SysUser user) {
		List<Order> entityList = new ArrayList<>();
		for (Order order : list) {
			order.setModifier(user.getId());
			order.setModifyTime(DateUtil.getStringDate());
			entityList.add(order);
		}
		boolean updateBatchById = this.updateBatchById(entityList);
		return updateBatchById;
	}

	/**
	 * 分页查询
	 * <p>Title: list</p>   
	 * <p>Description: </p>   
	 * @param req
	 * @return
	 * @throws Exception   
	 */
	@Override
	public Page<Order> list(Req<Order> req) {
		EntityWrapper<Order> wrapper = new EntityWrapper<>();
		Order order = req.getData();
		wrapper.setEntity(order);
		Page<Order> selectPage = this.selectPage(new Page<>(req.getCurrentPage(), req.getPageSize()), wrapper);
		return selectPage;
	}
	/**
	 * 批量删除
	 * <p>Title: del</p>   
	 * <p>Description: </p>   
	 * @param order
	 * @return
	 * @throws Exception   
	 */
	@Override
	public boolean del(Order order) {
		EntityWrapper<Order> wrapper = new EntityWrapper<>();
		wrapper.setEntity(order);
		boolean delete = this.delete(wrapper);
		return delete;
	}
	/**
	 * 查询
	 * <p>Title: getList</p>   
	 * <p>Description: </p>   
	 * @param map
	 * @return   
	 */

	@Override
	public List<Map<String,Object>> getList(Map<String, String> map) {
		List<Map<String,Object>> object= orderMapper.getList(map);
		return object;
	}
	/**
	 * 编辑
	 * @Title: edit   
	 * @param order
	 * @param user 
	 * @return
	 * @throws Exception      
	 * @return: Result      
	 * @throws
	 * @author: sl.qiu
	 */
	@Override
	public Result edit(Order order,SysUser user)
			throws Exception {
			order.setModifier(user.getId());
			order.setModifyTime(DateUtil.getStringDate());
			orderMapper.updateByPrimaryKeySelective(order);
			return new Result().setCode(ResultCode.SUCCESS).setMessage("执行编辑操作出成功！");


	}
}
