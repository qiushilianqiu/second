package com.gantang.common.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 统一API响应结果封装
 */
@Data
public class Result {
    private static final int SUCCESS = 200;//成功
    private int code;
    private String message;
    private Object data;
    private Boolean flag =true ;
    public Result setCode(int code) {
        this.code = code;
        this.flag = SUCCESS == code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
