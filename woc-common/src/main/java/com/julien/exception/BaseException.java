package com.julien.exception;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String errMsg) {
        super(errMsg);
    }

}
