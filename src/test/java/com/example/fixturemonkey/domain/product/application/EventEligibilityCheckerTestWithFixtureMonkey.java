package com.example.fixturemonkey.domain.product.application;

import com.example.fixturemonkey.domain.member.application.MemberService;
import com.example.fixturemonkey.domain.member.mock.FakeMemberRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class EventEligibilityCheckerTestWithFixtureMonkey {

    private FixtureMonkey fixtureMonkey;
    private MemberService memberService;
    private EventEligibilityChecker eventEligibilityChecker;

    @BeforeEach
    void setUp() {
        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
                .build();

        memberService = new MemberService(new FakeMemberRepository());
        eventEligibilityChecker = fixtureMonkey.giveMeBuilder(EventEligibilityChecker.class)
                .set("vipMemberIds", "1,2,3")
                .sample();
    }

    @Test
    void testCanParticipate_whenVipMember_shouldReturnFalse() {
        // given: VIP 멤버 ID
        int vipMemberId = 2;
        LocalDateTime eventStartTime = LocalDateTime.now().minusDays(1);
        LocalDateTime eventEndTime = LocalDateTime.now().plusDays(1);

        // when: canParticipate 호출
        boolean result = eventEligibilityChecker.canParticipate(vipMemberId, eventStartTime, eventEndTime);

        // then: VIP 멤버는 참여 불가능
        assertThat(result).isFalse();
    }

    @Test
    void testCanParticipate_whenAdminMember_shouldReturnFalse() {
        // given: 관리자 멤버 설정
        int adminMemberId = 99;
        LocalDateTime eventStartTime = LocalDateTime.now().minusDays(1);
        LocalDateTime eventEndTime = LocalDateTime.now().plusDays(1);

        // when: canParticipate 호출
        boolean result = eventEligibilityChecker.canParticipate(adminMemberId, eventStartTime, eventEndTime);

        // then: 관리자 멤버는 참여 불가능
        assertThat(result).isFalse();
    }

    @Test
    void testCanParticipate_whenOutsideEventPeriod_shouldReturnFalse() {
        // given: 이벤트 기간 외부의 시간
        int normalMemberId = 4;
        LocalDateTime eventStartTime = LocalDateTime.now().plusDays(1); // 이벤트 시작 전
        LocalDateTime eventEndTime = LocalDateTime.now().plusDays(2);

        // when: canParticipate 호출
        boolean result = eventEligibilityChecker.canParticipate(normalMemberId, eventStartTime, eventEndTime);

        // then: 이벤트 기간 외부는 참여 불가능
        assertThat(result).isFalse();
    }

    @Test
    void testCanParticipate_whenEligibleMember_shouldReturnTrue() {
        // given: 일반 멤버 설정
        int normalMemberId = 101;
        LocalDateTime eventStartTime = LocalDateTime.now().minusDays(1);
        LocalDateTime eventEndTime = LocalDateTime.now().plusDays(1);

        // when: canParticipate 호출
        boolean result = eventEligibilityChecker.canParticipate(normalMemberId, eventStartTime, eventEndTime);

        // then: 일반 멤버는 참여 가능
        assertThat(result).isTrue();
    }
}
