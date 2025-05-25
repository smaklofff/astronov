package com.askme.astronov.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@Configuration
public class SecretsConfig {

    @NotBlank(message = "giga.chat.client_id is required")
    @Value("${giga.chat.client_id}")
    private String gigaChatClientId;

    @NotBlank(message = "giga.chat.auth.token is required")
    @Value("${giga.chat.auth.token}")
    private String gigaChatAuthToken;

    @NotBlank(message = "yandex.gpt.auth.token is required")
    @Value("${yandex.gpt.auth.token}")
    private String yandexGptAuthToken;

    @NotBlank(message = "yandex.gpt.folder_id is required")
    @Value("${yandex.gpt.folder_id}")
    private String yandexGptFolderId;

    @NotBlank(message = "mistral.gpt.api.key is required")
    @Value("${mistral.gpt.api.key}")
    private String mistralGptApiKey;
}
