package com.bookhub.service.download;

import com.bookhub.domain.DownloadTask;
import com.bookhub.util.threading.ThreadPools;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 下载服务骨架，演示多线程 + 观察者模式的使用。
 */
public class DownloadService {

    private final Logger logger;
    private final Map<String, DownloadTask> tasks = new ConcurrentHashMap<>();

    public DownloadService(Logger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    public DownloadTask createTask(String url, String targetPath, long totalSize) {
        DownloadTask task = new DownloadTask(url, url, targetPath, totalSize);
        tasks.put(task.getId(), task);
        return task;
    }

    public void startTask(DownloadTask task, DownloadTask.ProgressListener listener) {
        Objects.requireNonNull(task);
        task.markRunning();
        ThreadPools.downloadExecutor().submit(() -> simulateDownload(task, listener));
    }

    private void simulateDownload(DownloadTask task, DownloadTask.ProgressListener listener) {
        logger.info("开始模拟下载: {}", task.getSourceUrl());
        long chunk = Math.max(task.getTotalSize() / 10, 1);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                task.markFailed();
                return;
            }
            task.addProgress(chunk, listener);
        }
        task.markCompleted();
        logger.info("下载完成: {}", task.getTargetPath());
    }
}
