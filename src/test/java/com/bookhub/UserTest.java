package com.bookhub;

import com.bookhub.domain.User;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void builderSetsDefaultsAndSearchableText() {
        User user = User.builder()
                .id("u1")
                .username("alice")
                .passwordHash("hash")
                .interests(Set.of("java", "oop"))
                .build();

        assertEquals("alice", user.getUsername());
        assertEquals("reader", user.getRole());
        assertNotNull(user.getCreatedAt());
        assertEquals("alice reader", user.searchableText());
        assertEquals(Set.of("java", "oop"), user.getInterests());
    }
}

