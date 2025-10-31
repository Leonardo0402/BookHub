package com.bookhub.util.exception;

/**
 * 网络通信相关异常。
 */
public class NetworkException extends BookHubException {
    public NetworkException(String message, Throwable cause) {
        super("NETWORK_ERROR", message, cause);
    }
}
