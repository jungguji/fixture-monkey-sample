package com.example.fixturemonkey.domain.member.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    private long id;
    private String name;
    private String email;
    private String phoneNumber;
    private int age;
    private boolean isVerified;
    private LocalDateTime createdAt;

    public MemberEntity(String name, String email, String phoneNumber, int age, boolean isVerified) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.isVerified = isVerified;
        this.createdAt = LocalDateTime.now();
    }

    public void verify() {
        this.isVerified = true;
    }
}
