package com.bookhub.domain;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * {@code MediaItem} 是图书、音频、视频等资源的抽象基类。
 *
 * <p>它演示了以下知识点：</p>
 * <ul>
 *     <li>抽象类：定义公共属性与行为，子类（Book、Audio、Video）共享。</li>
 *     <li>访问控制：字段使用 {@code private}，通过受保护的访问器暴露给子类。</li>
 *     <li>不可变对象：除标签集合外字段设计为不可变，展示 defensive copy。</li>
 * </ul>
 */
public abstract class MediaItem implements Searchable, Exportable {

    private final String id;
    private final String title;
    private final String author;
    private final int publishYear;
    private final Instant createdAt;
    private final Set<String> tags;

    protected MediaItem(String id, String title, String author, int publishYear, Instant createdAt, Collection<String> tags) {
        this.id = Objects.requireNonNull(id, "id");
        this.title = Objects.requireNonNull(title, "title");
        this.author = Objects.requireNonNullElse(author, "Unknown");
        this.publishYear = publishYear;
        this.createdAt = Objects.requireNonNullElse(createdAt, Instant.now());
        this.tags = tags == null ? new LinkedHashSet<>() : new LinkedHashSet<>(tags);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * @return 标签的不可变视图，防止外部修改破坏内部状态。
     */
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    @Override
    public Collection<String> tags() {
        return getTags();
    }

    @Override
    public String searchableText() {
        return String.join(" ", title, author, String.valueOf(publishYear));
    }

    /**
     * 子类可覆盖此方法提供特定媒体的短描述。
     */
    public String shortDescription() {
        return title + " (" + publishYear + ")";
    }

    @Override
    public String toString() {
        return shortDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaItem that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
