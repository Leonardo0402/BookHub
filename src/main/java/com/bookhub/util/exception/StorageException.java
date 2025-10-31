package com.bookhub.util.exception;

/**
 * I/O 层封装的异常，展示异常翻译：将底层 IOException 等转换为统一异常。
 */
public class StorageException extends BookHubException {
    public StorageException(String message, Throwable cause) {
        super("STORAGE_ERROR", message, cause);
    }
}
