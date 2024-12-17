package com.example.fixturemonkey.domain.member.infrastructure;

import com.example.fixturemonkey.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository jpaRepository;

    @Override
    public Optional<MemberEntity> findById(long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public MemberEntity save(MemberEntity entity) {
        return jpaRepository.save(entity);
    }
}
