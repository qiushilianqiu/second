package com.gantang.interfaces.controller;

import com.gantang.common.redis.RedisUtil;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultGenerator;
import com.gantang.common.util.CollectionUtil;
import com.gantang.common.util.F;
import com.gantang.entity.test.Test;
import com.gantang.interfaces.service.CommonService;
import com.gantang.interfaces.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sl.qiu
 * @description 公共接口
 * @date 2019/4/29
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    CommonService commonService;
    @Autowired
    TestService testService;
    @Autowired
    private HttpSession session;
    /**
     * TODO:测试消息队列
     *
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:18
     */
    @RequestMapping("/sendQueueMessage")
    @ResponseBody
    public Result sendQueueMessage() {
        System.out.println("消息服务开始");
        commonService.sendQueueMessage("firstQuenu","你好啊第一次队列消息");
        return ResultGenerator.genSuccessResult("nihai");
    }
    /**
     * TODO:测试服务间调用
     *
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:18
     */
    @RequestMapping("/orderInterface")
    @ResponseBody
    public Result orderInterface(@RequestParam("id") @PathVariable String id) {
        System.out.println("接口服务" + id);
        commonService.testTx();
        return ResultGenerator.genSuccessResult("nihai");
    }

    /**
     * TODO:测试
     *
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:18
     */
    @RequestMapping("/test1")
    @ResponseBody
    public Result test() {
        Map<String, String> selectMap = new HashMap<>();
        selectMap.put("table", "sys_user");
        List<Map<String, Object>> load = commonService.load("sys_user", new F[]{
        });
        return ResultGenerator.genSuccessResult(load);
    }

    /**
     * TODO:测试
     *
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:18
     */
    @RequestMapping("/test2")
    @ResponseBody
    public Result test2() throws Exception {
        Test test = new Test();

        List<Test> tests = testService.queryList(test);
        return ResultGenerator.genSuccessResult(tests);

    }

    /**
     * TODO:获取字典表数据
     *
     * @param map
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:18
     */
    @RequestMapping("/getDictionList")
    @ResponseBody
    public Result getDIctionList(@RequestParam Map<String, String> map) {

        List<Map<String, Object>> dictionList = commonService.getDictionList(map);
        return ResultGenerator.genSuccessResult(dictionList);

    }

    /**
     * /**
     * TODO:获取单个字典表数据
     *
     * @param map
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:18
     */
    @RequestMapping("/getDictionOne")
    @ResponseBody
    public Result getDictionOne(@RequestParam Map<String, String> map) {

        List<Map<String, Object>> dictionList = commonService.getDictionList(map);
        if (CollectionUtil.isNotNullList(dictionList)) {
            Map<String, Object> stringStringMap = dictionList.get(0);
            return ResultGenerator.genSuccessResult(stringStringMap);
        } else {
            return ResultGenerator.genSuccessResult(dictionList);
        }

    }

    /**
     * TODO: 获取任意表信息
     *
     * @param map
     * @return com.gantang.common.result.Result
     * @author sl.qiu
     * @date 2020/4/24 16:50
     */
    @RequestMapping("/getEveryTableList")
    @ResponseBody
    public Result getEveryTableList(@RequestParam Map<String, String> map) {

        List<Map<String, Object>> everyTableList = commonService.getEveryTableList(map);
        return ResultGenerator.genSuccessResult(everyTableList);
    }

    /**
     * TODO: 获取任意单条记录信息
     *
     * @param map
     * @return java.lang.String
     * @author sl.qiu
     * @date 2020/4/24 16:51
     */
    @RequestMapping("/getEveryTable")
    @ResponseBody
    public Result getEveryTable(@RequestParam Map<String, String> map) {

        List<Map<String, Object>> everyTableList = commonService.getEveryTableList(map);
        if (CollectionUtil.isNotNullList(everyTableList)) {
            Map<String, Object> stringStringMap = everyTableList.get(0);
            return ResultGenerator.genSuccessResult(stringStringMap);
        } else {
            return ResultGenerator.genSuccessResult(everyTableList);
        }

    }
}

