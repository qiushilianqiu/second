package com.gantang.common.result;

/**
 * 响应码枚举，参考HTTP状态码的语义,
 */
public  class ResultCode {
    public static final int SUCCESS = 200;//成功
    public static final int FAIL = 400;//失败
    public static final int UNAUTHORIZED = 401;//未认证（签名错误）
    public static final int NOT_FOUND = 404;//接口不存在
    public static final int INTERFACE_SERVER_ERROR = 405;//调用系统其他微服务异常
    public static final int CHECK_ANOMALY = 406;//实体类校验异常
    public static final int INTERNAL_SERVER_ERROR = 500;//服务器内部错误
//    SUCCESS(200),//成功
//    FAIL(400),//失败
//    UNAUTHORIZED(401),//未认证（签名错误）
//    NOT_FOUND(404),//接口不存在
//    INTERNAL_SERVER_ERROR(500);//服务器内部错误
//
//    private final int code;
//
//    ResultCode(int code) {
//        this.code = code;
//    }
//
//    public int code() {
//        return code;
//    }
}
