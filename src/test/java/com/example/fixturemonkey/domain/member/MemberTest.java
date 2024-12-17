package com.example.fixturemonkey.domain.member;

import com.example.fixturemonkey.domain.member.domain.Member;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberTest {

    @Test
    void testGetAgeCategory() {
        // 청소년
        Member minor = new Member(1L, "Alice", "alice@example.com", "123456789", 15, true, LocalDateTime.now());
        assertEquals("청소년", minor.getAgeCategory());

        // 청년
        Member adult = new Member(2L, "Bob", "bob@example.com", "987654321", 30, true, LocalDateTime.now());
        assertEquals("청년", adult.getAgeCategory());

        // 시니어
        Member senior = new Member(3L, "Charlie", "charlie@example.com", "555555555", 70, true, LocalDateTime.now());
        assertEquals("시니어", senior.getAgeCategory());
    }
}