package com.gantang.management.controller;

import com.gantang.common.annotation.SysLog;
import com.gantang.common.redis.RedisUtil;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import com.gantang.common.util.DateUtil;
import com.gantang.entity.management.SysUser;
import com.gantang.management.mapper.SysUserMapper;
import com.gantang.management.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称): parent
 * @Package(包名称) com.gantang.controller
 * @ClassName(类名称):  SysUserController
 * @Title(标题):用户表 控制层
 * @see(与该类相关联的类):
 * @author(作者): 深圳市甘棠餐饮集团 sl.qiu
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
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 分页查询
     * @Title: list
     * @param req
     * @return
     * @return: Page<GuantTask>
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年12月6日 下午3:51:10
     */
//	@RequestMapping("/list")
//	@RequestMapping("/list")
//	public Page<SysUser> list(@RequestBody Req<SysUser> req) {
//	   Page<SysUser> page = sysUserService.list(req);
//   return page;
//	}

    /**
     * 单个添加
     * @Title: add
     * @param sysUser
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午3:51:43
     */
    @RequestMapping("/add")
    @SysLog("用户表添加")
    public Result add(@RequestBody SysUser sysUser, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        sysUser.setModifyTime(DateUtil.getStringDate());
        sysUser.setCreater(131);
        sysUser.setCreateTime(DateUtil.getStringDate());
        sysUser.setModifier(1312);

        try {
            int id = sysUserService.add(sysUser);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());

            return ResultGenerator.genFailResult("执行失败");
        }

        return ResultGenerator.genSuccessResult("执行成功!");
    }

    /**
     * 删除
     * @Title: del
     * @param sysUser
     * @return
     * @return: Object
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午3:52:27
     */
    @RequestMapping("/del")
    @SysLog("用户表删除")
    public Result del(@RequestBody SysUser sysUser, HttpServletRequest request) {

        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean del = sysUserService.del(sysUser);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());

            return ResultGenerator.genFailResult("执行失败");
        }

        return ResultGenerator.genSuccessResult("执行成功!");
    }

    /**
     * 更新方法
     * @Title: updateBatch
     * @param list
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年12月6日 下午4:21:02
     */
    @RequestMapping("/updateBatch")
    @SysLog("用户表编辑")
    public Result updateBatch(@RequestBody List<SysUser> list, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean updateBatch = sysUserService.updateBatch(list, user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("执行失败");
        }

        return ResultGenerator.genSuccessResult("执行成功!");

    }

    /**
     * 单条跟新
     * @Title: updateBatch
     * @param list
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年12月6日 下午4:21:02
     */
    @RequestMapping("/updateBatchById")
    @SysLog("用户表编辑")
    public Result updateBatchById(@RequestBody SysUser list, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean updateBatch = sysUserService.updateBatchById(list, user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("执行失败");
        }

        return ResultGenerator.genSuccessResult("执行成功!");

    }

    /**
     * 查询
     * @Title: queryList
     * @param sysUser
     * @return
     * @return: List<SysUser>
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午4:28:02
     */
    @RequestMapping("/queryList")
    public Result queryList(@RequestBody SysUser sysUser, HttpServletRequest request) {
        try {
            return ResultGenerator.genSuccessResult(sysUserService.queryList(sysUser));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     *
     * @Title: addBatch
     * @param list
     * @param request
     * @return
     * @return: Result
     * @throws
     * @author: sl.qiu
     * @date(创建日期):   2018年12月6日 下午5:26:34
     */
    @RequestMapping("/addBatch")
    @SysLog("用户表添加")
    public Result addBatch(@RequestBody List<SysUser> list, HttpServletRequest request) {

        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean addBatch = sysUserService.addBatch(list, user);
            return ResultGenerator.genSuccessResult("执行成功!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("执行失败");
        }


    }

    /**
     * 使用的查询列表
     * @Description: 查询列表
     * @param @param map
     * @param @param request
     * @param @return
     * @return Result
     */
    @RequestMapping("/getList")
    public Result getList(@RequestParam Map<String, String> map, HttpServletRequest request) {
        try {
            return ResultGenerator.genSuccessResult(sysUserService.getList(map));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     * 单个添加
     * @Title: add
     * @param sysUser
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午3:51:43
     */
    @RequestMapping("/edit")
    @SysLog("用户表编辑")
    public Result edit(@RequestBody SysUser sysUser, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            Result result = sysUserService.edit(sysUser, user);
            return ResultGenerator.genSuccessResult("执行成功!");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("执行失败");
        }


    }
}
