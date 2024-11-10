package com.askme.astronov.service.impl.yandexGpt;

import com.askme.astronov.dto.SurveyRequestDto;
import com.askme.astronov.dto.requests.YandexGptRequestDto;
import com.askme.astronov.dto.requests.YandexGptRequestDto.CompletionOptions;
import com.askme.astronov.dto.requests.YandexGptRequestDto.Message;
import com.askme.astronov.dto.responses.YandexGptResponseDto;
import com.askme.astronov.service.FiegnClients.yandexGpt.YandexFiegnClient;
import com.askme.astronov.service.GptModel;
import com.askme.astronov.utils.ConverterUtil;
import com.askme.astronov.utils.integration.RequestSenderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class YandexGptServiceImpl extends GptModel {

    private final YandexFiegnClient yandexFiegnClient;
    private final RequestSenderUtil requestSenderUtil;
    private final YandexAccessTokenService yandexAccessTokenService;

    @Override
    @SneakyThrows
    public <T> List<Map<String, T>> getSurvey(SurveyRequestDto surveyRequestDto) {
        log.info("My token: {}", yandexAccessTokenService.getIamToken());
        return processGptModelResponse(getYandexResponseDto(surveyRequestDto)).stream()
                .map(x -> ConverterUtil.convertToObject(x, new TypeReference<Map<String, T>>() {})).toList();
    }

    private YandexGptResponseDto getYandexResponseDto(SurveyRequestDto surveyRequestDto) {
        requestSenderUtil.setFiegnClient(yandexFiegnClient);
        log.info("Start getting yandexGpt chat response");
        return ConverterUtil.convertToObject(
                requestSenderUtil.sendRequest(
                        getBodyForGptModel(surveyRequestDto.getSystemText(), getUserTextForModel(surveyRequestDto)),
                        getHeadersForGptModel(yandexAccessTokenService.getIamToken())),
                new TypeReference<>() {}
        );
    }

    private List<String> processGptModelResponse(YandexGptResponseDto yandexGptResponseDto) {
        return yandexGptResponseDto.getAlternatives().stream().map(x -> x.getMessage().getText()).toList();
    }

    private Map<String, String> getHeadersForGptModel(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // ToDo Сделать так, чтобы этот параметр задавался через RequestInterceptor, где токен подтягивался из Секмана
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add("X-Request-ID", UUID.randomUUID().toString());
        httpHeaders.add("x-folder-id", "b1gcp00dali86t6r5vrf");
        return httpHeaders.toSingleValueMap();
    }

    private YandexGptRequestDto getBodyForGptModel(String systemText, String userText) {
        return YandexGptRequestDto.builder()
                .modelUri("gpt://b1gcp00dali86t6r5vrf/yandexgpt-lite/latest")
                .completionOptions(CompletionOptions.builder()
                        .stream(false)
                        .temperature(0.4)
                        .maxTokens("10000")
                        .build())
                .messages(List.of(
                            Message.builder()
                                    .role("system")
                                    .text(systemText)
                            .build(),
                            Message.builder()
                                    .role("user")
                                    .text(userText)
                                    .build()
                        ))
                .build();
    }
}
