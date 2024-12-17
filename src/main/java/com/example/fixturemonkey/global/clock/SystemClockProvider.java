package com.example.fixturemonkey.global.clock;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
class SystemClockProvider implements ClockProvider {

    @Override
    public OffsetDateTime now() {
        return OffsetDateTime.now();
    }
}
