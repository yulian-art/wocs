package com.julien.exception;

/**
 * 密码错误异常
 */
public class PasswordErrorException extends BaseException {

    public PasswordErrorException() {
    }

    public PasswordErrorException(String errMsg) {
        super(errMsg);
    }

}
