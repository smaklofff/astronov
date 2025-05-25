package com.askme.astronov.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "gpt.gigachat")
public record GigaChatProperties (
        @DefaultValue("GigaChat") String model,
        @DefaultValue("false") boolean stream,
        @DefaultValue("0") int updateInterval
) {}
