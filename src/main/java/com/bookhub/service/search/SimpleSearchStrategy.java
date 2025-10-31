package com.bookhub.service.search;

import com.bookhub.domain.MediaItem;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 基于 Stream API 的搜索实现，用以展示函数式编程风格。
 */
public class SimpleSearchStrategy implements SearchStrategy {

    @Override
    public List<MediaItem> search(List<MediaItem> source, Predicate<MediaItem> filter) {
        Predicate<MediaItem> safeFilter = filter == null ? item -> true : filter;
        return source.stream()
                .filter(safeFilter)
                .sorted(Comparator.comparing(media -> media.getTitle().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}
