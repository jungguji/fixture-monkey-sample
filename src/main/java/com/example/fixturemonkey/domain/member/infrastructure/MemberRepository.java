package com.example.fixturemonkey.domain.member.infrastructure;

import com.example.fixturemonkey.domain.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Optional<MemberEntity> findById(long id);

    MemberEntity save(MemberEntity member);
}
