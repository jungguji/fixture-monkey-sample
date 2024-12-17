package com.example.fixturemonkey.domain.product.application;

import com.example.fixturemonkey.domain.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class EventEligibilityChecker {

    private final MemberService memberRoleService;

    @Value("${event.vip.member.ids}")
    private String vipMemberIds;

    private static final ZoneOffset KST = ZoneOffset.of("+09:00");

    /**
     * 특정 이벤트 참여 가능 여부를 확인
     *
     * 조건:
     * - VIP 회원이거나
     * - 관리자 권한을 가진 회원이거나
     * - 이벤트 기간 외에 요청한 회원이라면 false
     *
     * 그렇지 않다면 true를 반환한다.
     *
     * @param memberId 확인할 회원 ID
     * @param eventStartTime 이벤트 시작 시간
     * @param eventEndTime 이벤트 종료 시간
     * @return 참여 가능 여부
     */
    public boolean canParticipate(int memberId, LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
        OffsetDateTime currentTime = OffsetDateTime.now();
        boolean isEligible = true;

        boolean isVipMember = checkVipMember(memberId);
        boolean isAdminMember =
                checkAdminMember(memberId);
        boolean isOutsideEventPeriod = checkOutsideEventPeriod(currentTime, eventStartTime, eventEndTime);

        if (isVipMember || isAdminMember || isOutsideEventPeriod) {
            isEligible = false;
        }

        return isEligible;
    }

    private boolean checkVipMember(int memberId) {
        return Arrays.stream(vipMemberIds.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .anyMatch(id -> id == memberId);
    }

    private boolean checkAdminMember(int memberId) {
        return memberRoleService.hasAdminRole(memberId);
    }

    private boolean checkOutsideEventPeriod(OffsetDateTime currentTime, LocalDateTime eventStartTime, LocalDateTime eventEndTime) {
        OffsetDateTime eventStart = eventStartTime.atOffset(KST);
        OffsetDateTime eventEnd = eventEndTime.atOffset(KST);
        return currentTime.isBefore(eventStart) || currentTime.isAfter(eventEnd);
    }
}
