package com.bookhub.util.exception;

/**
 * 当向库中添加重复条目时抛出。
 */
public class DuplicateItemException extends BookHubException {
    public DuplicateItemException(String message) {
        super("ITEM_DUPLICATE", message);
    }
}
