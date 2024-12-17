package com.example.fixturemonkey.domain.member.mock;

import com.example.fixturemonkey.domain.member.domain.Member;
import com.example.fixturemonkey.domain.member.infrastructure.MemberEntity;
import com.example.fixturemonkey.domain.member.infrastructure.MemberRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMemberRepository implements MemberRepository {

    private AtomicLong id = new AtomicLong(0);
    private Map<Long, MemberEntity> db = new ConcurrentHashMap<>();

    @Override
    public Optional<MemberEntity> findById(long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public MemberEntity save(MemberEntity entity) {
        db.put(entity.getId() != 0 ? entity.getId() : id.incrementAndGet(), entity);
        return entity;
    }
}
