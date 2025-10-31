package com.bookhub.domain;

import java.time.Instant;
import java.util.Objects;

/**
 * 借阅记录实体。展示组合关系（User + MediaItem）以及状态枚举的使用。
 */
public class BorrowRecord {

    public enum Status { BORROWED, RETURNED, OVERDUE }

    private final String id;
    private final String itemId;
    private final String userId;
    private final Instant borrowTime;
    private Instant returnTime;
    private Status status;

    public BorrowRecord(String id, String itemId, String userId, Instant borrowTime) {
        this.id = Objects.requireNonNull(id);
        this.itemId = Objects.requireNonNull(itemId);
        this.userId = Objects.requireNonNull(userId);
        this.borrowTime = Objects.requireNonNullElse(borrowTime, Instant.now());
        this.status = Status.BORROWED;
    }

    public String getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }

    public String getUserId() {
        return userId;
    }

    public Instant getBorrowTime() {
        return borrowTime;
    }

    public Instant getReturnTime() {
        return returnTime;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * 使用封装控制状态改变：归还时自动更新状态与时间。
     */
    public void markReturned(Instant returnTime) {
        this.returnTime = Objects.requireNonNullElse(returnTime, Instant.now());
        this.status = Status.RETURNED;
    }

    public void markOverdue() {
        this.status = Status.OVERDUE;
    }
}
