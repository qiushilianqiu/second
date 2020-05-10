package com.gantang.core.globalconfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gantang.common.exception.MyInterfaceException;
import com.gantang.common.exception.MyRuntimeException;
import com.gantang.common.exception.MyVerifyException;
import com.gantang.common.exception.ServiceException;
import com.gantang.common.redis.RedisUtil;
import com.gantang.common.result.Result;
import com.gantang.common.result.ResultCode;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;


/*
 * @Title(标题):  Spring MVC 配置,全局的异常处理
 * @see(与该类相关联的类):
 * @author(作者): sl.qiu
 * @since: JDK1.8
 * @Title(标题):
 * @author sl.qiu
 * @date 2020/4/27 14:28
 * @version(版本): V1.0
 * @Copyright(版权): 2018 www.gantang.com.cn Inc. All rights reserved.
 * @Description(描述):  工具类
 * TODO(这里描述这个文件做什么 – 可选)
 * 注意：本内容仅限于甘棠餐饮集团内部传阅，禁止外泄以及用于其他的商业项目
 * ==== All rights Reserved, Designed By www.huihe.com.cn ====
 *—————————————————————————————————————————————————————————————————
 *修改记录
 *    修改者：
 *    修改时间：
 *    复审人:
 *    修改原因：
 *
 *—————————————————————————————————————————————————————————————————
 */
@Configuration
public class WebmvcConfigurer implements  WebMvcConfigurer {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Value("${spring.profiles.active}")
    private String env;//当前激活的配置文件
    @Autowired
    public RedisUtil redisUtil;
    @Autowired
    private MessageSource messageSource;

    //使用阿里 FastJson 作为JSON MessageConverter
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,//保留空的字段
                SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
                SerializerFeature.WriteNullNumberAsZero);//Number null -> 0
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


    /**
     * TODO:  全局异常处理
     *
     * @param exceptionResolvers
     * @author sl.qiu
     * @date 2020/4/27 14:29
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                Result result = new Result();
                logger.info(e.getMessage());
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                    logger.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
                } else if (e instanceof ServletException) {
                    result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
                } else if (e instanceof MyVerifyException) {
                    result.setCode(ResultCode.CHECK_ANOMALY).setMessage("校验失败");
                }else if (e instanceof MyInterfaceException) {
                    result.setCode(ResultCode.INTERFACE_SERVER_ERROR).setMessage("服务调用第三方接口报错["+e.getMessage()+"]");
                } else if (e instanceof FeignException) {
                    result.setCode(ResultCode.INTERFACE_SERVER_ERROR).setMessage("服务调用第三方接口报错["+e.getMessage()+"]");
                }else if (e instanceof HystrixRuntimeException) {
                    result.setCode(ResultCode.INTERFACE_SERVER_ERROR).setMessage("服务调用第三方服务器停止或者超时["+e.getMessage()+"]");
                }else if (e instanceof MethodArgumentNotValidException) {
                    BindingResult resultss = ((MethodArgumentNotValidException) e).getBindingResult();
                    List<FieldError> fieldErrorList = resultss.getFieldErrors();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (FieldError fieldError : fieldErrorList) {
                        Locale currentLocale = LocaleContextHolder.getLocale();
                        String errorMessage = messageSource.getMessage(fieldError, currentLocale);
                        stringBuffer.append(errorMessage).append("</br>");
                        logger.info("adding error message - " + errorMessage + " - to errorsList");
                    }
                    result.setCode(ResultCode.CHECK_ANOMALY).setMessage(stringBuffer.toString());
                }  else if (e instanceof MyRuntimeException) {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getMessage());
                } else {
                    result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    logger.error(message, e);
                }
                responseResult(response, result);
                return new ModelAndView();
            }

        });
    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    //添加拦截器，网关路由已经做了拦截，则如有特殊业务则开启此拦截
/*
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //接口签名认证拦截器，该签名认证比较简单，实际项目中可以使用Json Web Token或其他更好的方式替代。
        //开发环境忽略签名认证
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                logger.info("拦截---》" + request.getRequestURI());
                if (request.getMethod().equals("OPTIONS")) {
                    return true;
                }
                //验证签名
                boolean pass = checkToken(request);
//                	boolean pass = true;
                if (pass) {
                    return true;
                } else {
                    logger.warn("签名认证失败，请求接口：{}，请求IP：{}，请求参数：{}",
                            request.getRequestURI(), getIpAddress(request), JSON.toJSONString(request.getParameterMap()));

                    Result result = new Result();
                    result.setCode(ResultCode.UNAUTHORIZED).setMessage("签名认证失败");
                    responseResult(response, result);
                    return false;
                }
            }
        }).excludePathPatterns("/api/login/*")
                .excludePathPatterns("/login/*")
                //图片展示
                .excludePathPatterns("/api/process/img/*")
                //流程图图片展示
                .excludePathPatterns("/api/process/track/img/*")
                .excludePathPatterns("/api/import/*")
                //桌面展示
                .excludePathPatterns("/api/downLoad/*")
                .excludePathPatterns("/person/*")
                //文件上传
                .excludePathPatterns("/api/upload/*")
                //招行摄像机
                .excludePathPatterns("/api/camera/getPeopleCount")
                .excludePathPatterns("/api/registered/apply")

                .excludePathPatterns("/api/common/getOrg")
                .excludePathPatterns("/api/registered/sendCode")
                //测试上传
                .excludePathPatterns("/api/common/updateSomeInformation")
                .excludePathPatterns("/api/loginYstWx/*");

    }
*/

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(result.getCode());
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * 一个简单的签名认证，规则：
     * 1. 将请求参数按ascii码排序
     * 2. 拼接为a=value&b=value...这样的字符串（不包含sign）
     * 3. 混合密钥（secret）进行md5获得签名，与请求的签名进行比较
     */
 /*   private boolean validateSign(HttpServletRequest request) {
        String requestSign = request.getParameter("sign");//获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        if (StringUtils.isEmpty(requestSign)) {
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        keys.remove("sign");//排除sign参数
        Collections.sort(keys);//排序

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key).append("=").append(request.getParameter(key)).append("&");//拼接字符串
        }
        String linkString = sb.toString();
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);//去除最后一个'&'

        String secret = "Potato";//密钥，自己修改
        String sign = DigestUtils.md5Hex(linkString + secret);//混合密钥md5

        return StringUtils.equals(sign, requestSign);//比较
    }*/

    /**
     * 校验token
     *
     * @param request
     * @return
     */
    private boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        System.out.println("校验token---》" + token);
        if (null == token || !redisUtil.hasKey(token)) {
            System.out.println("校验token---》false");
            return false;
        } else {
            System.out.println("获取当前token有效时间---》" + redisUtil.getExpire(token));
            //刷新token有效时长
            redisUtil.expire(token, 7200L);
            System.out.println("校验token---》success");
            return true;
        }
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户端ip
        if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(0, ip.indexOf(",")).trim();
        }

        return ip;
    }
}
