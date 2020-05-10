package com.gantang.common.annotation;

import java.lang.annotation.*;

/**
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):
 * @Package(包名称) .
 * @ClassName(类名称):Dto
 * @Title(标题):
 * @see(与该类相关联的类):
 * @author(作者):  sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2019/4/12 11:03
 * @version(版本): V1.0
 * @Copyright(版权):  www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):系统日志注解
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业目的
 *————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人:
 *    修改原因：
 *
 *——————————————————————————————————————
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
