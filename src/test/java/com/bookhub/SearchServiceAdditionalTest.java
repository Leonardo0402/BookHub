package com.bookhub;

import com.bookhub.domain.Book;
import com.bookhub.domain.MediaItem;
import com.bookhub.service.search.SearchService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceAdditionalTest {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(SearchServiceAdditionalTest.class);

    @Test
    void nullKeywordReturnsAllSortedByTitle() {
        SearchService service = new SearchService(logger);
        MediaItem a = new Book("id-a", "a", "auth", 2020, "", "", Instant.now(), Set.of());
        MediaItem b = new Book("id-b", "B", "auth", 2020, "", "", Instant.now(), Set.of());
        MediaItem c = new Book("id-c", "c", "auth", 2020, "", "", Instant.now(), Set.of());
        service.addItem(b);
        service.addItem(c);
        service.addItem(a);

        List<MediaItem> all = service.search((String) null);
        assertEquals(3, all.size());
        assertEquals("a", all.get(0).getTitle());
        assertEquals("B", all.get(1).getTitle());
        assertEquals("c", all.get(2).getTitle());

        // unmodifiable result
        assertThrows(UnsupportedOperationException.class, () -> all.add(a));
    }
}

