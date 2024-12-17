package com.example.fixturemonkey.domain.product.application;

import com.example.fixturemonkey.domain.member.application.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "event.vip.member.ids=1,2,3"
})
class EventEligibilityCheckerTest {

    @Autowired
    private EventEligibilityChecker eventEligibilityChecker;

    @Autowired
    private MemberService memberService;

    @ParameterizedTest(name = "{index} => memberId={0}, startTime={1}, endTime={2}, expectedResult={3}")
    @MethodSource("getMemberArguments")
    void testCanParticipate(int memberId, LocalDateTime startTime, LocalDateTime endTime, boolean expectedResult) {
        // when
        boolean result = eventEligibilityChecker.canParticipate(memberId, startTime, endTime);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    static Stream<Object[]> getMemberArguments() {
        return Stream.of(
                new Object[]{1, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), false}, // VIP 멤버
                new Object[]{99, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), false}, // 관리자 멤버
                new Object[]{4, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), false}, // 이벤트 기간 외
                new Object[]{101, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), true} // 일반 멤버 (참여 가능)
        );
    }
}