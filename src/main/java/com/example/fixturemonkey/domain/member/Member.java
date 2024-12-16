package com.example.fixturemonkey.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class Member {
    private final long id;
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final int age;
    private final boolean isVerified;
    private final LocalDateTime createdAt;

    public String getAgeCategory() {
        assert this.age >= 0;

        if (this.age < 18) {
            return "청소년";
        } else if (this.age < 65) {
            return "청년";
        } else {
            return "시니어";
        }
    }
}