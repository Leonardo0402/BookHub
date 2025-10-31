package com.bookhub;

import com.bookhub.domain.BorrowRecord;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class BorrowRecordTest {

    @Test
    void markReturnedAndOverdueUpdateStatusAndTime() {
        BorrowRecord record = new BorrowRecord("r-1", "i-1", "u-1", Instant.now());
        assertEquals(BorrowRecord.Status.BORROWED, record.getStatus());

        record.markReturned(null);
        assertEquals(BorrowRecord.Status.RETURNED, record.getStatus());
        assertNotNull(record.getReturnTime());

        record.markOverdue();
        assertEquals(BorrowRecord.Status.OVERDUE, record.getStatus());
    }
}

