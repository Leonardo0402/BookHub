package com.bookhub.service.search;

import com.bookhub.domain.MediaItem;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 搜索服务示例。内部维护一个媒体列表，提供添加、搜索等方法。
 */
public class SearchService {

    private final Logger logger;
    private final List<MediaItem> items = new ArrayList<>();
    private SearchStrategy strategy = new SimpleSearchStrategy();

    public SearchService(Logger logger) {
        this.logger = Objects.requireNonNull(logger);
    }

    public void setStrategy(SearchStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public void addItem(MediaItem item) {
        items.add(item);
        logger.info("添加媒体条目: {}", item);
    }

    public List<MediaItem> search(String keyword) {
        String safeKeyword = Objects.requireNonNullElse(keyword, "");
        String needle = safeKeyword.toLowerCase(Locale.ROOT);
        Predicate<MediaItem> filter = media -> media.searchableText().toLowerCase(Locale.ROOT).contains(needle);
        return Collections.unmodifiableList(strategy.search(items, filter));
    }

    public List<MediaItem> search(Predicate<MediaItem> filter) {
        return Collections.unmodifiableList(strategy.search(items, filter));
    }

    public List<MediaItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
