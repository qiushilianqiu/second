package com.gantang.common.result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    public static final int SUCCESS = 200;//成功
    public static final int FAIL = 400;//失败
    public static final int UNAUTHORIZED = 401;//未认证（签名错误）
    public static final int NOT_FOUND = 404;//接口不存在
    public static final int INTERFACE_SERVER_ERROR = 405;//调用系统其他微服务异常
    public static final int CHECK_ANOMALY = 406;//实体类校验异常
    public static final int INTERNAL_SERVER_ERROR = 500;//服务器内部错误

    public static Result genSuccessResult() {
        return new Result()
                .setCode(SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genMsgSuccessResult(String message) {
        return new Result()
                .setCode(SUCCESS)
                .setMessage(message);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(FAIL)
                .setMessage(message);
    }
    public static Result genFailInterfaceResult(String message) {
        return new Result()
                .setCode(INTERFACE_SERVER_ERROR)
                .setMessage(message);
    }
    public static Result genNotFoundResult(String message) {
        return new Result()
                .setCode(NOT_FOUND)
                .setMessage(message);
    }

    public static Result genServerErrorResult(String message) {
        return new Result()
                .setCode(INTERNAL_SERVER_ERROR)
                .setMessage(message);
    }

    public static Result genResult(boolean flag, String message) {
        if(flag){
            return genMsgSuccessResult(message + "成功！");
        } else {
            return genFailResult(message + "失败！");
        }
    }
}
