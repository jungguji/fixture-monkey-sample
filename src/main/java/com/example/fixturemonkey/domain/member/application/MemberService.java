package com.example.fixturemonkey.domain.member.application;


import com.example.fixturemonkey.domain.member.domain.Member;
import com.example.fixturemonkey.domain.member.infrastructure.MemberEntity;
import com.example.fixturemonkey.domain.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(MemberEntity entity) {
        return Member.of(memberRepository.save(entity));
    }

    /**
     * 특정 ID를 가진 회원을 활성화(verified 상태로 변경)
     * @param memberId 회원 ID
     * @return 활성화된 회원
     */
    @Transactional(rollbackFor = Exception.class)
    public Member activateMember(long memberId) {
        // ID로 회원 조회
        MemberEntity entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID: " + memberId));

        // 이미 활성화된 회원인지 확인
        if (entity.isVerified()) {
            throw new IllegalStateException("이미 활성화된 회원입니다: " + memberId);
        }

        // 활성화
        entity.verify();
        entity = memberRepository.save(entity);
        return Member.of(entity);
    }

    public boolean hasAdminRole(long memberId) {
        return memberId < 100;
    }
}
