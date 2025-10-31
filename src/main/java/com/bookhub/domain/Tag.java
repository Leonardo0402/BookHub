package com.bookhub.domain;

import java.util.Objects;

/**
 * 标签值对象。通过重写 equals/hashCode 支持在 Set 中去重。
 */
public final class Tag {

    private final String value;

    public Tag(String value) {
        this.value = Objects.requireNonNull(value).toLowerCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag tag)) return false;
        return value.equals(tag.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
