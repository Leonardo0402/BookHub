package com.bookhub.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

/**
 * 下载任务模型，演示内部类、观察者模式与多线程通信的基础。
 */
public class DownloadTask {

    private final String id;
    private final String sourceUrl;
    private final String targetPath;
    private final long totalSize;
    private final Instant createdAt;
    private final AtomicLong completedBytes = new AtomicLong();
    private volatile Status status = Status.PENDING;

    public enum Status { PENDING, RUNNING, PAUSED, COMPLETED, FAILED }

    public DownloadTask(String id, String sourceUrl, String targetPath, long totalSize) {
        this.id = Objects.requireNonNull(id);
        this.sourceUrl = Objects.requireNonNull(sourceUrl);
        this.targetPath = Objects.requireNonNull(targetPath);
        this.totalSize = totalSize;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public long getCompletedBytes() {
        return completedBytes.get();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void markRunning() {
        status = Status.RUNNING;
    }

    public void markPaused() {
        status = Status.PAUSED;
    }

    public void markCompleted() {
        status = Status.COMPLETED;
    }

    public void markFailed() {
        status = Status.FAILED;
    }

    public void addProgress(long delta, ProgressListener listener) {
        long current = completedBytes.addAndGet(delta);
        if (listener != null) {
            listener.onProgress(Math.min(current, totalSize), totalSize);
        }
    }

    /**
     * 函数式接口便于与 Lambda 结合，UI 层可直接传入方法引用。
     */
    @FunctionalInterface
    public interface ProgressListener {
        void onProgress(long completedBytes, long totalBytes);

        static ProgressListener logging(Consumer<String> logger, DownloadTask task) {
            Objects.requireNonNull(logger);
            Objects.requireNonNull(task);
            return (completed, total) -> {
                double percent = total <= 0 ? 0 : completed * 100.0 / total;
                logger.accept("任务 " + task.id + " 完成 " + String.format("%.2f", percent) + "%");
            };
        }
    }
}
