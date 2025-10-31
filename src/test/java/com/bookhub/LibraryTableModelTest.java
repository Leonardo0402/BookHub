package com.bookhub;

import com.bookhub.domain.Book;
import com.bookhub.domain.MediaItem;
import com.bookhub.ui.model.LibraryTableModel;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTableModelTest {

    @Test
    void tableModelReflectsItemsAndValues() {
        LibraryTableModel model = new LibraryTableModel();
        MediaItem b1 = new Book("b1", "Clean Code", "Robert C. Martin", 2008, "", "",
                Instant.now(), Set.of("clean", "code"));
        MediaItem b2 = new Book("b2", "Effective Java", "Joshua Bloch", 2018, "", "",
                Instant.now(), Set.of("java"));

        model.setItems(List.of(b1, b2));
        assertEquals(2, model.getRowCount());
        assertEquals(4, model.getColumnCount());
        assertEquals("ID", model.getColumnName(0));
        assertEquals("标题", model.getColumnName(1));

        assertEquals("b1", model.getValueAt(0, 0));
        assertEquals("Clean Code", model.getValueAt(0, 1));
        assertEquals("Robert C. Martin", model.getValueAt(0, 2));
        assertEquals(2008, model.getValueAt(0, 3));
    }
}

