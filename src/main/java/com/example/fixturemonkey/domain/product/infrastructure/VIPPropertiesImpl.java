package com.example.fixturemonkey.domain.product.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "event.vip.member")
class VIPPropertiesImpl implements VIPProperties {

    private final String ids;

    @ConstructorBinding
    public VIPPropertiesImpl(String ids) {
        this.ids = ids;
    }

    @Override
    public List<Long> getIds() {
        return Arrays.stream(this.ids.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
    }
}