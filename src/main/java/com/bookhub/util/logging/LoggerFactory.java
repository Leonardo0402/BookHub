package com.bookhub.util.logging;

import org.slf4j.Logger;

/**
 * 包装 SLF4J 的工厂方法，便于替换或扩展。演示封装常用库的技巧，便于未来切换实现。
 */
public final class LoggerFactory {

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> type) {
        return org.slf4j.LoggerFactory.getLogger(type);
    }
}
