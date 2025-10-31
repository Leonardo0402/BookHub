package com.bookhub.util.exception;

/**
 * 自定义运行时异常基类，封装统一的错误码与消息结构。
 */
public class BookHubException extends RuntimeException {

    private final String errorCode;

    public BookHubException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BookHubException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
