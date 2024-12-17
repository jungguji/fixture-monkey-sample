package com.example.fixturemonkey.domain.product.application;

import com.example.fixturemonkey.domain.member.application.MemberService;
import com.example.fixturemonkey.domain.member.mock.FakeMemberRepository;
import com.example.fixturemonkey.domain.product.infrastructure.FakeVIPProperties;
import com.example.fixturemonkey.global.clock.FakeSystemClockProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EventEligibilityCheckerV2Test {

    private EventEligibilityCheckerV2 eventEligibilityChecker;

    @BeforeEach
    void setUp() {
        int year = 2024;
        int month = 12;
        int day =13;
        int hour = 11;
        int minute = 11;
        int second = 11;

        eventEligibilityChecker = new EventEligibilityCheckerV2(
                new MemberService(new FakeMemberRepository())
                , new FakeVIPProperties(Arrays.asList(1L,2L,3L))
                , new FakeSystemClockProvider(OffsetDateTime.of(year, month, day, hour, minute, second, 11, ZoneOffset.UTC))
        );
    }

    @ParameterizedTest(name = "{index} => memberId={0}, startTime={1}, endTime={2}, expectedResult={3}")
    @MethodSource("getMemberArguments")
    void testCanParticipate(int memberId, LocalDateTime startTime, LocalDateTime endTime, boolean expectedResult) {
        // when
        boolean result = eventEligibilityChecker.canParticipate(memberId, startTime, endTime);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Object[]> getMemberArguments() {
        int year = 2024;
        int month = 12;
        int day =13;
        int hour = 11;
        int minute = 11;
        int second = 11;

        LocalDateTime eventStartDate = LocalDateTime.of(year, month, day-1, hour, minute, second);
        LocalDateTime eventEndDate = LocalDateTime.of(year, month, day+1, hour, minute, second);

        return Stream.of(
                new Object[]{1, eventStartDate, eventEndDate, false}, // VIP 멤버
                new Object[]{99, eventStartDate, eventEndDate, false}, // 관리자 멤버
                new Object[]{4, eventEndDate.plusDays(1), eventEndDate.plusDays(2), false}, // 이벤트 기간 외
                new Object[]{101, eventStartDate, eventEndDate, true} // 일반 멤버 (참여 가능)
        );
    }
}