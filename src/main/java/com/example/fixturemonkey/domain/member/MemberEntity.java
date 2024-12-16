package com.example.fixturemonkey.domain.member;

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
}
