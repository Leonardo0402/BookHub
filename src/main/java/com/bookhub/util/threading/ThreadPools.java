package com.bookhub.util.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 提供用于下载、索引等任务的线程池。演示单例/懒加载与 JVM 关闭钩子结合的技巧。
 */
public final class ThreadPools {

    private static final ExecutorService downloadExecutor = Executors.newFixedThreadPool(4);
    private static final ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(2);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            downloadExecutor.shutdown();
            scheduledExecutor.shutdown();
            try {
                downloadExecutor.awaitTermination(5, TimeUnit.SECONDS);
                scheduledExecutor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "bookhub-threadpool-shutdown"));
    }

    private ThreadPools() {
    }

    public static ExecutorService downloadExecutor() {
        return downloadExecutor;
    }

    public static ScheduledExecutorService scheduledExecutor() {
        return scheduledExecutor;
    }
}
