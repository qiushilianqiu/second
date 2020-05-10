package com.gantang.management.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gantang.common.annotation.SysLog;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultCode;
import com.gantang.common.util.DateUtil;
import com.gantang.common.util.Req;
import com.gantang.entity.management.SysUser;
import com.gantang.management.mapper.SysUserMapper;
import com.gantang.management.service.SysUserService;
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
 * @ClassName(类名称):SysUserServiceImpl
 * @Title(标题):  SysUserServiceImpl.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团 sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2020-04-30 09:55:11
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{
	@Autowired
	SysUserMapper sysUserMapper;
	/**
	 * 无分页的查询
	 * <p>Title: queryList</p>   
	 * <p>Description: </p>   
	 * @param sysUser
	 * @return
	 * @throws Exception   
	 */
	 
	@Override
	public List<SysUser> queryList(SysUser sysUser) {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<>();

					sysUser.setId(sysUser.getId());
					sysUser.setName(sysUser.getName());
					sysUser.setCarId(sysUser.getCarId());
					sysUser.setModifier(sysUser.getModifier());
					sysUser.setModifyTime(sysUser.getModifyTime());
					sysUser.setCreater(sysUser.getCreater());
					sysUser.setCreateTime(sysUser.getCreateTime());
				wrapper.setEntity(sysUser);
		List<SysUser> selectList = this.selectList(wrapper);
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
	public boolean addBatch(List<SysUser> list,SysUser user) {
		List<SysUser> uList = new ArrayList<>();
		for (SysUser sysUser : list) {
            sysUser.setModifier(user.getId());
			sysUser.setModifyTime(DateUtil.getStringDate());
			sysUser.setCreater(user.getId());
			sysUser.setCreateTime(DateUtil.getStringDate());
			uList.add(sysUser);
		}
		boolean insertBatch = this.insertBatch(uList);
		return insertBatch;
	}

	/**
	 * 单增
	 * <p>Title: add</p>   
	 * <p>Description: </p>   
	 * @param sysUser
	 * @return
	 * @throws Exception   
	 */
	@Override
	public int add(SysUser sysUser) {

		boolean insert = this.insert(sysUser);
		int id=sysUser.getId();
		return id;
	}
		/**
	 * 单条跟新
	 * <p>Title: updateBatch</p>   
	 * <p>Description: </p>   
	 * @param sysUser
	 * @return
	 * @throws Exception   
	 */
	@Override
	public boolean updateBatchById(SysUser sysUser,SysUser user) {

			sysUser.setModifier(user.getId());
			sysUser.setModifyTime(DateUtil.getStringDate());
			boolean updateBatchById = this.updateById(sysUser);
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
	public boolean updateBatch(List<SysUser> list,SysUser user) {
		List<SysUser> entityList = new ArrayList<>();
		for (SysUser sysUser : list) {
			sysUser.setModifier(user.getId());
			sysUser.setModifyTime(DateUtil.getStringDate());
			entityList.add(sysUser);
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
	public Page<SysUser> list(Req<SysUser> req) {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
		SysUser sysUser = req.getData();
		wrapper.setEntity(sysUser);
		Page<SysUser> selectPage = this.selectPage(new Page<>(req.getCurrentPage(), req.getPageSize()), wrapper);
		return selectPage;
	}
	/**
	 * 批量删除
	 * <p>Title: del</p>   
	 * <p>Description: </p>   
	 * @param sysUser
	 * @return
	 * @throws Exception   
	 */
	@Override
	public boolean del(SysUser sysUser) {
		EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
		wrapper.setEntity(sysUser);
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
		List<Map<String,Object>> object= sysUserMapper.getList(map);
		return object;
	}
	/**
	 * 编辑
	 * @Title: edit   
	 * @param sysUser
	 * @param user 
	 * @return
	 * @throws Exception      
	 * @return: Result      
	 * @throws
	 * @author: sl.qiu
	 */
	@Override
	public Result edit(SysUser sysUser,SysUser user)
			throws Exception {
			sysUser.setModifier(user.getId());
			sysUser.setModifyTime(DateUtil.getStringDate());
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
			return new Result().setCode(ResultCode.SUCCESS).setMessage("执行编辑操作出成功！");


	}
}
