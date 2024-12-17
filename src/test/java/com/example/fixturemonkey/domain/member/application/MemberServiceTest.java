package com.example.fixturemonkey.domain.member.application;

import com.example.fixturemonkey.domain.member.domain.Member;
import com.example.fixturemonkey.domain.member.infrastructure.MemberEntity;
import com.example.fixturemonkey.domain.member.infrastructure.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testActivateMember() {
        MemberEntity member = new MemberEntity("Alice", "alice@example.com", "123456789", 25, false);
        memberRepository.save(member);

        // ID 기반으로 활성화 테스트
        Member activatedMember = memberService.activateMember(member.getId());
        assertTrue(activatedMember.isVerified());
    }
}