package com.example.fixturemonkey.global.clock;


import java.time.OffsetDateTime;

public class FakeSystemClockProvider implements ClockProvider {

    private OffsetDateTime offsetDateTime;

    public FakeSystemClockProvider(OffsetDateTime offsetDateTime) {
        this.offsetDateTime = offsetDateTime;
    }

    @Override
    public OffsetDateTime now() {
        return this.offsetDateTime;
    }
}