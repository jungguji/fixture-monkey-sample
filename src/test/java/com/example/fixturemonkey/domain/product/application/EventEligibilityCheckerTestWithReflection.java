package com.example.fixturemonkey.domain.product.application;

import com.example.fixturemonkey.domain.member.application.MemberService;
import com.example.fixturemonkey.domain.member.mock.FakeMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class EventEligibilityCheckerTestWithReflection {

    private EventEligibilityChecker eventEligibilityChecker;

    @BeforeEach
    void setUp() {
        eventEligibilityChecker = new EventEligibilityChecker(new MemberService(new FakeMemberRepository()));

        // ReflectionTestUtils를 사용해 @Value 필드 설정
        ReflectionTestUtils.setField(eventEligibilityChecker, "vipMemberIds", "1,2,3");
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
        return Stream.of(
                new Object[]{1, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), false}, // VIP 멤버
                new Object[]{99, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), false}, // 관리자 멤버
                new Object[]{4, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), false}, // 이벤트 기간 외
                new Object[]{101, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1), true} // 일반 멤버 (참여 가능)
        );
    }
}
