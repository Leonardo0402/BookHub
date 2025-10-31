package com.bookhub.service.index;

import com.bookhub.domain.MediaItem;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 背景索引服务示例，演示生产者-消费者队列的使用。
 */
public class IndexerService {

    private final Logger logger;
    private final BlockingQueue<MediaItem> queue = new LinkedBlockingQueue<>();

    public IndexerService(Logger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    public void submit(MediaItem item) {
        queue.offer(item);
    }

    public void start(Path indexDirectory) {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    MediaItem item = queue.take();
                    logger.info("索引媒体条目: {} -> {}", item.getTitle(), indexDirectory);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "bookhub-indexer");
        worker.setDaemon(true);
        worker.start();
    }
}
