package com.bookhub.ui.controller;

import com.bookhub.domain.MediaItem;
import com.bookhub.service.search.SearchService;
import com.bookhub.ui.model.LibraryTableModel;

import java.util.List;
import java.util.Objects;

/**
 * MVC 中的 Controller，负责协调服务层与视图层。
 */
public class MainController {

    private final SearchService searchService;
    private final LibraryTableModel tableModel;

    public MainController(SearchService searchService, LibraryTableModel tableModel) {
        this.searchService = Objects.requireNonNull(searchService);
        this.tableModel = Objects.requireNonNull(tableModel);
    }

    public void loadInitialData(List<MediaItem> demoItems) {
        demoItems.forEach(searchService::addItem);
        tableModel.setItems(searchService.getItems());
    }

    public void performSearch(String keyword) {
        tableModel.setItems(searchService.search(keyword));
    }
}
