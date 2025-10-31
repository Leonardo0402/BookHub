package com.bookhub.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

/**
 * 用户实体示例。使用不可变设计 + builder 演示封装与可读性。
 */
public final class User implements Searchable {

    private final String id;
    private final String username;
    private final String passwordHash;
    private final String role;
    private final Instant createdAt;
    private final Set<String> interests;

    private User(Builder builder) {
        this.id = Objects.requireNonNull(builder.id, "id");
        this.username = Objects.requireNonNull(builder.username, "username");
        this.passwordHash = Objects.requireNonNull(builder.passwordHash, "passwordHash");
        this.role = builder.role == null ? "reader" : builder.role;
        this.createdAt = builder.createdAt == null ? Instant.now() : builder.createdAt;
        this.interests = Set.copyOf(builder.interests);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public Set<String> getInterests() {
        return interests;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String searchableText() {
        return username + " " + role;
    }

    @Override
    public Set<String> tags() {
        return interests;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String id;
        private String username;
        private String passwordHash;
        private String role;
        private Instant createdAt;
        private Set<String> interests = Set.of();

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder interests(Set<String> interests) {
            this.interests = interests == null ? Set.of() : Set.copyOf(interests);
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
