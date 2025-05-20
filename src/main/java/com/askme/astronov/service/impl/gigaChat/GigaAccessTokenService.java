package com.askme.astronov.service.impl.gigaChat;

import com.askme.astronov.config.SecretsConfig;
import com.askme.astronov.service.feignClients.gigaChat.GigaChatAccessTokenFeignClient;
import com.askme.astronov.utils.ConverterUtil;
import com.askme.astronov.utils.integration.RequestSenderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;


@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class GigaAccessTokenService {

    private final RequestSenderUtil requestSenderUtil;
    private final GigaChatAccessTokenFeignClient gigaAccessToken;
    private final SecretsConfig secretsConfig;

    private String accessToken;

    private static final String BODY_FOR_ACCESS_TOKEN = "scope=GIGACHAT_API_PERS";

    @Scheduled(fixedRate = 1600000)
    private void updateAccessToken() {
        log.info("Start getting access token for GigaChat");
        Map<String, String> response = ConverterUtil.convertToObject(
                requestSenderUtil.sendRequest(gigaAccessToken, BODY_FOR_ACCESS_TOKEN, getHeadersForAccessToken()),
                new TypeReference<>() {}
        );
        accessToken = response.get("access_token");
        log.info("The end of getting access token for GigaChat");
    }

    private Map<String, String> getHeadersForAccessToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + secretsConfig.getGigaChatAuthToken());
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        httpHeaders.add("RqUID", UUID.randomUUID().toString());
        return httpHeaders.toSingleValueMap();
    }
}
