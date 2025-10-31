package com.bookhub.util.exception;

/**
 * 元数据格式不合法时使用的检查型异常示例。
 */
public class InvalidMetadataException extends Exception {
    public InvalidMetadataException(String message) {
        super(message);
    }

    public InvalidMetadataException(String message, Throwable cause) {
        super(message, cause);
    }
}
