package com.example.fixturemonkey.global.clock;

import java.time.OffsetDateTime;

public interface ClockProvider {
    OffsetDateTime now();
}
