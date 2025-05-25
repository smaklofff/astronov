package com.askme.astronov.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "gpt.mistral")
public record MistralProperties(
        @DefaultValue("ministral-8b-latest") String model,
        @DefaultValue("0.3") double temperature,
        @DefaultValue("3") int n
) {
}
