package com.bookhub.domain;

import java.time.Instant;
import java.util.Collection;

/**
 * 代表纸质或电子书籍。演示如何使用 {@code super} 调用父类构造方法并扩展额外字段。
 */
public class Book extends MediaItem {

    private final String isbn;
    private final String summary;

    public Book(String id, String title, String author, int publishYear, String isbn, String summary,
                Instant createdAt, Collection<String> tags) {
        super(id, title, author, publishYear, createdAt, tags);
        this.isbn = isbn;
        this.summary = summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public String searchableText() {
        return super.searchableText() + " " + summary;
    }

    @Override
    public String toJson() {
        return "{" +
                "\"type\":\"book\"," +
                "\"id\":\"" + getId() + "\"," +
                "\"title\":\"" + getTitle() + "\"," +
                "\"author\":\"" + getAuthor() + "\"," +
                "\"isbn\":\"" + (isbn == null ? "" : isbn) + "\"" +
                "}";
    }

    @Override
    public String defaultExportFilename() {
        return getTitle().replaceAll("\\s+", "_") + "-book.json";
    }

    @Override
    public java.nio.file.Path associatedFile() {
        return null;
    }
}
