package com.bookhub;

import com.bookhub.domain.DownloadTask;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class DownloadTaskTest {

    @Test
    void progressListenerReportsClampedPercentAndStatusTransitions() {
        DownloadTask task = new DownloadTask("task-1", "http://example", "file", 100);
        AtomicReference<String> lastMsg = new AtomicReference<>();
        DownloadTask.ProgressListener listener = DownloadTask.ProgressListener.logging(lastMsg::set, task);

        assertEquals(0, task.getCompletedBytes());
        task.addProgress(40, listener);
        assertEquals(40, task.getCompletedBytes());
        assertTrue(lastMsg.get().contains("40.00%"));

        task.addProgress(70, listener); // exceeds total
        assertEquals(110, task.getCompletedBytes());
        assertTrue(lastMsg.get().contains("100.00%"));

        task.markRunning();
        assertEquals(DownloadTask.Status.RUNNING, task.getStatus());
        task.markPaused();
        assertEquals(DownloadTask.Status.PAUSED, task.getStatus());
        task.markCompleted();
        assertEquals(DownloadTask.Status.COMPLETED, task.getStatus());
        task.markFailed();
        assertEquals(DownloadTask.Status.FAILED, task.getStatus());
    }
}

