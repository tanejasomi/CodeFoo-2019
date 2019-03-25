package com.codefoo.exception;

public class AppException extends Exception {

    public AppException(String s) {
        super(s);
    }

    public AppException(Throwable cause) {
        super(cause);

    }
}
