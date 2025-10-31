package com.bookhub.app;

import com.bookhub.config.AppConfig;
import com.bookhub.domain.Audio;
import com.bookhub.domain.Book;
import com.bookhub.domain.MediaItem;
import com.bookhub.service.search.SearchService;
import com.bookhub.ui.controller.MainController;
import com.bookhub.ui.model.LibraryTableModel;
import com.bookhub.ui.view.MainFrame;
import org.slf4j.Logger;

import javax.swing.*;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * 应用入口：负责初始化配置、装配模块，并启动 CLI 或 GUI。
 */
public class BookHubApplication {

    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        SearchService searchService = config.searchService();
        Logger logger = org.slf4j.LoggerFactory.getLogger(BookHubApplication.class);

        List<MediaItem> demoItems = List.of(
                new Book("book-001", "Java 编程思想", "Bruce Eckel", 2019, "9787111213826",
                        "经典的 Java 进阶书籍", Instant.now(), Set.of("java", "oop")),
                new Audio("audio-001", "Design Patterns", "GoF", 1994, Path.of("/tmp/design-patterns.mp3"),
                        3600, "John Doe", Instant.now(), Set.of("pattern"))
        );

        LibraryTableModel tableModel = new LibraryTableModel();
        MainController controller = new MainController(searchService, tableModel);
        controller.loadInitialData(demoItems);

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(controller, tableModel);
            frame.setVisible(true);
        });

        logger.info("BookHub 应用已启动");
    }
}
