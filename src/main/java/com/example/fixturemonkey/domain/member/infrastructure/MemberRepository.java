package com.example.fixturemonkey.domain.member.infrastructure;

import java.util.Optional;

public interface MemberRepository {

    Optional<MemberEntity> findById(long id);

    MemberEntity save(MemberEntity member);
}
