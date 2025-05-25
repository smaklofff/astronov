package com.askme.astronov.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "gpt.yandex")
public record YandexGptProperties(
        @DefaultValue("false") boolean stream,
        @DefaultValue("0.4") double temperature,
        @DefaultValue("10000") String maxTokens
) { }
