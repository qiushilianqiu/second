package com.gantang.management.controller;

import com.gantang.common.annotation.SysLog;
import com.gantang.common.redis.RedisUtil;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import com.gantang.common.util.DateUtil;
import com.gantang.entity.management.Order;
import com.gantang.entity.management.SysUser;
import com.gantang.management.mapper.SysUserMapper;
import com.gantang.management.service.OrderService;
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
 * @ClassName(类名称):  OrderController
 * @Title(标题):订单表 控制层
 * @see(与该类相关联的类):
 * @author(作者): 深圳市甘棠餐饮集团 sl.qiu
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
@RestController
@RequestMapping("/order")
public class OrderController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    private OrderService orderService;

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
//	public Page<Order> list(@RequestBody Req<Order> req) {
//	   Page<Order> page = orderService.list(req);
//   return page;
//	}

    /**
     * 单个添加
     * @Title: add
     * @param order
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午3:51:43
     */
    @RequestMapping("/add")
//    @SysLog("订单表添加")
    public Result add(@RequestBody Order order, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        order.setModifyTime(DateUtil.getStringDate());
        order.setCreater(123);
        order.setCreateTime(DateUtil.getStringDate());
        order.setModifier(123);
        try {
            int id = orderService.add(order);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());

            return ResultGenerator.genFailResult(e.getMessage());
        }

        return ResultGenerator.genSuccessResult("执行成功!");
    }

    /**
     * 删除
     * @Title: del
     * @param order
     * @return
     * @return: Object
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午3:52:27
     */
    @RequestMapping("/del")
    @SysLog("订单表删除")
    public Result del(@RequestBody Order order, HttpServletRequest request) {

        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean del = orderService.del(order);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());

            return ResultGenerator.genFailResult(e.getMessage());
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
    @SysLog("订单表编辑")
    public Result updateBatch(@RequestBody List<Order> list, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean updateBatch = orderService.updateBatch(list, user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult(e.getMessage());
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
    @SysLog("订单表编辑")
    public Result updateBatchById(@RequestBody Order list, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean updateBatch = orderService.updateBatchById(list, user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult(e.getMessage());
        }

        return ResultGenerator.genSuccessResult("执行成功!");

    }

    /**
     * 查询
     * @Title: queryList
     * @param order
     * @return
     * @return: List<Order>
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午4:28:02
     */
    @RequestMapping("/queryList")
    public Result queryList(@RequestBody Order order, HttpServletRequest request) {
        try {
            return ResultGenerator.genSuccessResult(orderService.queryList(order));
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
    @SysLog("订单表添加")
    public Result addBatch(@RequestBody List<Order> list, HttpServletRequest request) {

        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            boolean addBatch = orderService.addBatch(list, user);
            return ResultGenerator.genSuccessResult("执行成功!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult(e.getMessage());
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
            return ResultGenerator.genSuccessResult(orderService.getList(map));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     * 单个添加
     * @Title: add
     * @param order
     * @return
     * @return: String
     * @throws
     * @author: sl.qiu
     * @date(创建日期): 2018年12月6日 下午3:51:43
     */
    @RequestMapping("/edit")
    @SysLog("订单表编辑")
    public Result edit(@RequestBody Order order, HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader("token"));
        try {
            Result result = orderService.edit(order, user);
            return ResultGenerator.genSuccessResult("执行成功!");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultGenerator.genFailResult(e.getMessage());
        }


    }
}
