package com.julien.result;

import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Boolean success;
    private Integer errCode; //编码：1成功，0和其它数字为失败
    private String errMsg; //错误信息
    private T data; //数据


    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.success = true;
        result.errCode = 1;
        result.errMsg = "success";

        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.success = true;
        result.data = object;
        result.errCode = 1;
        result.errMsg = "success";
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.success = true;
        result.errMsg = msg;
        result.errCode = 0;
        result.errMsg = "";
        return result;
    }

}
