package com.bookhub;

import com.bookhub.domain.Book;
import com.bookhub.domain.MediaItem;
import com.bookhub.service.search.SearchService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchServiceTest {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(SearchServiceTest.class);

    @Test
    void searchByKeywordFindsMatchingItems() {
        SearchService service = new SearchService(logger);
        MediaItem item = new Book("id-1", "Java 核心技术", "Cay Horstmann", 2020, "", "", Instant.now(), Set.of("java"));
        service.addItem(item);

        List<MediaItem> result = service.search("Java");
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
    }
}
