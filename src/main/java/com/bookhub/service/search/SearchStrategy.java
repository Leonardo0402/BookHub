package com.bookhub.service.search;

import com.bookhub.domain.MediaItem;

import java.util.List;
import java.util.function.Predicate;

/**
 * 搜索策略接口，展示策略模式 + 函数式编程。不同策略可组合 Predicate。
 */
@FunctionalInterface
public interface SearchStrategy {

    List<MediaItem> search(List<MediaItem> source, Predicate<MediaItem> filter);

    /**
     * 默认实现：先过滤再按标题排序。
     */
    default List<MediaItem> filterAndSort(List<MediaItem> source, Predicate<MediaItem> filter) {
        return search(source, filter == null ? item -> true : filter);
    }
}
