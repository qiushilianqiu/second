package com.gantang.common.system;

import com.gantang.common.util.DateUtil;
import lombok.Data;

/*
*
 * @Title(标题): 系统日志
 * @author sl.qiu
 * @date 2020/4/23 11:10
 * @param
 * @return
 */
@Data
public class SystemLog { 

	private String id; // id
	
	private String apiUrl; // 接口url
	
	private String opTime; // 操作时间
	
	private String opUser; // 操作人
	
	private String opDescribe; // 操作描述
	
	private String logType; // 日志类型
	
	private String opIp; // 操作ip
	
	private String errorMsg; // 错误摘要
	
	public static final String OP_LOG = "1"; //操作日志
	
	public static final String ERROR_LOG = "2"; //错误日志
	
	public static final String ABNORMAL_LOG = "3"; //异常操作日志

	private String useTime;


	/**
	 * 操作提交的数据
	 */
	private String params;


	/**

	 * 操作方式
	 */
	private String httpMethod;
	/**
	 * 请求类型.方法
	 */
	private String classMethod;
	/**
	 * 返回内容
	 */
	private String response;
	/**
	 * 有参数构造函数，实现给private成员变量传参数值的共同
	 * @param opUser
	 * @param apiUrl
	 */
    public SystemLog(String opUser, String apiUrl, String opIp){
    	this.opUser=opUser;
    	this.apiUrl=apiUrl;
    	this.opIp=opIp;
    	this.opTime= DateUtil.getStringDate().toString();
    }

	/**
	 * 有参数构造函数，实现给private成员变量传参数值的功能成功
	 * @param opUser
	 * @param apiUrl
	 * @param opDescribe
	 */
    public SystemLog(String opUser, String apiUrl, String opIp, String opDescribe){
    	this.opUser=opUser;
    	this.apiUrl=apiUrl;
    	this.opDescribe=opDescribe;
    	this.opIp=opIp;
    	this.logType=OP_LOG;
    	this.opTime=DateUtil.getStringDate().toString();        
    }
	/**
	 * 有参数构造函数，实现给private成员变量传参数值的功能失败
	 * @param opUser
	 * @param apiUrl
	 * @param opDescribe
	 */
    public SystemLog(String opUser, String apiUrl, String opIp, String opDescribe, String errorMsg){
    	this.opUser=opUser;
    	this.apiUrl=apiUrl;
    	this.opDescribe=opDescribe;
    	this.errorMsg=errorMsg;
    	this.logType=ERROR_LOG;
    	this.opIp=opIp;
    	this.opTime=DateUtil.getStringDate().toString();        
    }
    public SystemLog(){             
 
    }

}
