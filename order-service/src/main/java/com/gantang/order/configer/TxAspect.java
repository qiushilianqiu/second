package com.gantang.order.configer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 *
 * @ProjectName(项目名称): parent
 * @Package(包名称): com.gantang.order.configer
 * @ClassName(类名称): TxAspect
 * @Title(标题): 防止全局异常处理后，服务间事务处理不成功
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期): 2020/4/27 17:29
 * @version(版本): V1.0
 * @Copyright(版权): www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):该类的具体作用 注意：本内容仅限于甘棠公司内部传阅，禁止外泄以及用于其他的商业目的
 * ————————————————————————————————————
 * 修改记录
 * 修改者：
 * 修改时间：
 * 复审人:
 * 修改原因：
 * <p>
 * ——————————————————————————————————————
 */
//@Aspect
//@Component
public class TxAspect {
    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("@annotation(com.codingapi.txlcn.tc.annotation.LcnTransaction)") //只需拦截使用了LcnTransaction注解的方法
    public void BrokerAspect() {

    }

    /**
     * @description 在连接点执行之前执行的通知
     */
    @Before("BrokerAspect()")
    public void doBefore() {
        System.out.println(" 在连接点执行之前执行的通知 ");
    }

    /**
     * @description 在连接点执行之后执行的通知（返回通知和异常通知的异常）
     */
    @After("BrokerAspect()")
    public void doAfter() {
        System.out.println("在连接点执行之后执行的通知！");
    }

    /**
     * @description 在连接点执行之后执行的通知（返回通知）
     */

    @AfterReturning(returning = "rvt", pointcut = "@annotation(com.codingapi.txlcn.tc.annotation.LcnTransaction)")
    public void doAfterReturning(JoinPoint joinPoint, Object rvt) {
        //rvt为返回值
        System.out.println("在连接点执行之后执行的通知（返回通知）");
    }

    /**
     * @description 在连接点执行之后执行的通知（异常通知）
     */
    @AfterThrowing("BrokerAspect()")
    public void doAfterThrowing() {

        System.out.println("在连接点执行之后执行的通知（异常通知）！");
    }

//    /**
//     * @description  在连接点执行之后执行的通知（返回通知）
//     */
//    @AfterReturning(returning="rvt", pointcut="@annotation(com.codingapi.txlcn.tc.annotation.LcnTransaction)")
//    public void doAfterReturningGame(JoinPoint joinPoint, Object rvt){
//        //rvt 为返回值 这里为如果调用服务的返回值不为10000就进行回滚，说明调用的服务发生错误
//        System.out.println(rvt.toString());
//        if(JsonUtils.jsonToPojo(JsonUtils.objectToJson(rvt),ResponseResult.class).getCode() != 10000){
//            DTXUserControls.rollbackGroup(TracingContext.tracing().groupId());
//            System.out.println("回滚成功");
//        }
//        System.out.println("返回通知：在连接点执行之后执行的通知！");
//    }
}

