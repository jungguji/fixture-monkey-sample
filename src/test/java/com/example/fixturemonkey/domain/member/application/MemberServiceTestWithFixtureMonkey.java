package com.example.fixturemonkey.domain.member.application;

import com.example.fixturemonkey.domain.member.domain.Member;
import com.example.fixturemonkey.domain.member.infrastructure.MemberEntity;
import com.example.fixturemonkey.domain.member.mock.FakeMemberRepository;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTestWithFixtureMonkey {

    private FixtureMonkey fixtureMonkey;
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
                .build();

        memberService = new MemberService(new FakeMemberRepository());
    }

    @RepeatedTest(value = 100)
    void testActivateMemberUsingFixtureMonkey() {
        // given
        MemberEntity entity = fixtureMonkey.giveMeBuilder(MemberEntity.class)
                .set("isVerified", false)
                .sample();

        Member savedMember = memberService.save(entity);

        // when
        Member activatedMember = memberService.activateMember(savedMember.getId());

        // then
        assertThat(activatedMember.isVerified()).isTrue();
        assertThat(entity.getId()).isEqualTo(activatedMember.getId());
    }
}
