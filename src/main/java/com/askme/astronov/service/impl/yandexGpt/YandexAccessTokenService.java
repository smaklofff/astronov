//package com.askme.astronov.service.impl.yandexGpt;
//
//import com.askme.astronov.config.SecretsConfig;
//import com.askme.astronov.dto.requests.YandexGptAccessTokenDto;
//import com.askme.astronov.service.feignClients.yandexGpt.YandexGptAccessTokenFeignClient;
//import com.askme.astronov.utils.ConverterUtil;
//import com.askme.astronov.utils.integration.RequestSenderUtil;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@Getter
//@RequiredArgsConstructor
//@Service
//public class YandexAccessTokenService {
//
//    private final RequestSenderUtil requestSenderUtil;
//    private final YandexGptAccessTokenFeignClient yandexAccessToken;
//    private final SecretsConfig secretsConfig;
//    private String iamToken;
//
//    @Scheduled(fixedRate = 3200000)
//    private void updateAccessToken() {
//        log.info("Start getting access token for GigaChat");
//        Map<String, String> response = ConverterUtil.convertToObject(
//                requestSenderUtil.sendRequest(yandexAccessToken, createRequestBody(), getHeadersForAccessToken()),
//                new TypeReference<>() {}
//        );
//        iamToken = response.get("iamToken");
//        log.info("The end of getting access token for GigaChat");
//    }
//
//    private Map<String, String> getHeadersForAccessToken() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
//        httpHeaders.add("RqUID", UUID.randomUUID().toString());
//        return httpHeaders.toSingleValueMap();
//    }
//
//    private YandexGptAccessTokenDto createRequestBody() {
//        return YandexGptAccessTokenDto.builder()
//                .yandexPassportOauthToken(secretsConfig.getYandexGptAuthToken())
//                .build();
//    }
//}
