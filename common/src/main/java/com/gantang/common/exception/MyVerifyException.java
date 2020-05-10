/**  
 * All rights Reserved, Designed By www.gantang.com.cn
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.exception
 * @ClassName(类名称):HeHeVerifyException
 * @Title(标题):  HeHeVerifyException.java   
 * @see(与该类相关联的类):  
 * @author(作者):  深圳市甘棠餐饮集团有限公司   sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午9:11:06   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):    TODO(用一句话描述该文件做什么)  
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
package com.gantang.common.exception;

/**  
 * ==== All rights Reserved, Designed By www.gantang.com.cn ====
 * @ProjectName(项目名称):parent
 * @Package(包名称) com.gantang.util.exception
 * @ClassName(类名称):HeHeVerifyException
 * @Title(标题):  HeHeVerifyException.java   
 * @see(与该类相关联的类):  
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @date(创建日期):   2018年7月23日 上午9:11:06   
 * @version(版本): V1.0 
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述): 自定义异验证异常  
 * 注意：本内容仅限于甘棠餐饮集团有限公司内部传阅，禁止外泄以及用于其他的商业项目
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
public class MyVerifyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyVerifyException() {
        super();
    }

    public MyVerifyException(String message) {
        super(message);
    }

    public MyVerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyVerifyException(Throwable cause) {
        super(cause);
    }
}
