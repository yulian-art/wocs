package com.julien.exception;

public class UserNotLoginException extends BaseException {

    public UserNotLoginException() {
    }

    public UserNotLoginException(String errMsg) {
        super(errMsg);
    }

}
