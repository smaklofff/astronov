//package com.askme.astronov.service.impl.yandexGpt;
//
//import com.askme.astronov.config.SecretsConfig;
//import com.askme.astronov.dto.SurveyRequestDto;
//import com.askme.astronov.dto.requests.YandexGptRequestDto;
//import com.askme.astronov.dto.requests.YandexGptRequestDto.CompletionOptions;
//import com.askme.astronov.dto.requests.YandexGptRequestDto.Message;
//import com.askme.astronov.dto.responses.YandexGptResponseDto;
//import com.askme.astronov.properties.YandexGptProperties;
//import com.askme.astronov.service.feignClients.yandexGpt.YandexGptFeignClient;
//import com.askme.astronov.service.GptModel;
//import com.askme.astronov.utils.ConverterUtil;
//import com.askme.astronov.utils.integration.RequestSenderUtil;
//import com.fasterxml.jackson.core.type.TypeReference;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class YandexGptServiceImpl extends GptModel {
//
//    private final YandexGptFeignClient yandexFeignClient;
//    private final RequestSenderUtil requestSenderUtil;
//    private final YandexAccessTokenService yandexAccessTokenService;
//    private final YandexGptProperties yandexGptProperties;
//    private final SecretsConfig secretsConfig;
//
//    @Override
//    public <T> List<Map<String, T>> getSurvey(SurveyRequestDto surveyRequestDto) {
//        log.debug("My token: {}", yandexAccessTokenService.getIamToken());
//        return processGptModelResponse(getYandexResponseDto(surveyRequestDto)).stream()
//                .map(x -> ConverterUtil.convertToObject(x, new TypeReference<Map<String, T>>() {})).toList();
//    }
//
//    private YandexGptResponseDto getYandexResponseDto(SurveyRequestDto surveyRequestDto) {
//        log.info("Start getting yandexGpt chat response");
//        return ConverterUtil.convertToObject(
//                requestSenderUtil.sendRequest(
//                        yandexFeignClient,
//                        getBodyForGptModel(surveyRequestDto.getSystemText(), getUserTextForModel(surveyRequestDto)),
//                        getHeadersForGptModel(yandexAccessTokenService.getIamToken())),
//                new TypeReference<>() {}
//        );
//    }
//
//    private List<String> processGptModelResponse(YandexGptResponseDto yandexGptResponseDto) {
//        return yandexGptResponseDto.getResult().getAlternatives().stream()
//                .map(x -> extractContentBetweenBraces(x.getMessage().getText())).toList();
//    }
//
//    private Map<String, String> getHeadersForGptModel(String accessToken) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
//        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
//        httpHeaders.add("X-Request-ID", UUID.randomUUID().toString());
//        httpHeaders.add("x-folder-id", secretsConfig.getYandexGptFolderId());
//        return httpHeaders.toSingleValueMap();
//    }
//
//    private YandexGptRequestDto getBodyForGptModel(String systemText, String userText) {
//        return YandexGptRequestDto.builder()
//                .modelUri(String.format("gpt://%s/yandexgpt-lite/latest", secretsConfig.getYandexGptFolderId()))
//                .completionOptions(CompletionOptions.builder()
//                        .stream(yandexGptProperties.stream())
//                        .temperature(yandexGptProperties.temperature())
//                        .maxTokens(yandexGptProperties.maxTokens())
//                        .build())
//                .messages(List.of(
//                            Message.builder()
//                                    .role("system")
//                                    .text(systemText)
//                            .build(),
//                            Message.builder()
//                                    .role("user")
//                                    .text(userText)
//                                    .build()
//                        ))
//                .build();
//    }
//}
