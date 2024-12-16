package com.example.fixturemonkey.domain.member;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.PriorityConstructorArbitraryIntrospector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.navercorp.fixturemonkey.api.expression.JavaGetterMethodPropertySelector.javaGetter;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTestWithFixtureMonkey {

    private FixtureMonkey fixtureMonkey;

    @BeforeEach
    void setUp() {
        fixtureMonkey = FixtureMonkey.builder()
                .objectIntrospector(
                        PriorityConstructorArbitraryIntrospector.INSTANCE
                            .withParameterNamesResolver(constructor -> List.of("id", "name", "email", "phoneNumber", "age", "verified", "createdAt"))
                )
                .build();
    }

    @ParameterizedTest(name = "{index} => age{0}, expectedCategory={1}")
    @MethodSource("getMemberArguments")
    void testGetAgeCategory(int age, String expectedCategory) {
        // given
        Member member = fixtureMonkey.giveMeBuilder(Member.class)
                .set(javaGetter(Member::getId), 1L)
                .set("age", age)
                .sample();

        // when
        String category = member.getAgeCategory();

        // then
        assertThat(category).isEqualTo(expectedCategory);
    }

    private static Stream<Arguments> getMemberArguments() {
        return Stream.of(
                Arguments.of(15, "청소년"),
                Arguments.of(30, "청년"),
                Arguments.of(70, "시니어")
        );
    }
}