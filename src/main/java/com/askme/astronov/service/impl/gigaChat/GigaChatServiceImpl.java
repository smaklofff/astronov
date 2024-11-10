package com.askme.astronov.service.impl.gigaChat;

import com.askme.astronov.dto.SurveyRequestDto;
import com.askme.astronov.dto.requests.GigaChatRequestDto;
import com.askme.astronov.dto.requests.GigaChatRequestDto.Message;
import com.askme.astronov.dto.responses.GigaChatResponseDto;
import com.askme.astronov.service.FiegnClients.gigaChat.GigaFiegnClient;
import com.askme.astronov.service.GptModel;
import com.askme.astronov.utils.ConverterUtil;
import com.askme.astronov.utils.integration.RequestSenderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Slf4j
@Service
public class GigaChatServiceImpl extends GptModel {

    private final GigaFiegnClient gigaFiegnClient;
    private final RequestSenderUtil requestSenderUtil;
    private final GigaAccessTokenService gigaAccessTokenService;

    @Override
    @SneakyThrows
    public <T> List<Map<String, T>> getSurvey(SurveyRequestDto surveyRequestDto) {
        log.info("My token:" + gigaAccessTokenService.getAccessToken());
        return processGptModelResponse(getGigaChatResponseDto(surveyRequestDto)).stream()
                .map(x -> ConverterUtil.convertToObject(x, new TypeReference<Map<String, T>>() {})).toList();
    }

    private GigaChatResponseDto getGigaChatResponseDto(SurveyRequestDto surveyRequestDto) {
        requestSenderUtil.setFiegnClient(gigaFiegnClient);
        log.info("Start getting giga chat response");
        return ConverterUtil.convertToObject(
                requestSenderUtil.sendRequest(
                    getBodyForGptModel(surveyRequestDto.getSystemText(), getUserTextForModel(surveyRequestDto)),
                    getHeadersForGptModel(gigaAccessTokenService.getAccessToken())),
                new TypeReference<>() {}
        );
    }

    private List<String> processGptModelResponse(GigaChatResponseDto gigaChatResponseDto) {
        return gigaChatResponseDto.getChoices().stream()
                .map(choice -> extractContentBetweenBraces(choice.getMessage().getContent())).toList();
    }

    private Map<String, String> getHeadersForGptModel(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        // ToDo Сделать так, чтобы этот параметр задавался через RequestInterceptor, где токен подтягивался из Секмана
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add("X-Request-ID", UUID.randomUUID().toString());
        httpHeaders.add("X-Session-ID", UUID.randomUUID().toString());
        httpHeaders.add("X-Client-ID", "d30ffcb7-5370-4b54-b116-e74cd421dd3c");
        return httpHeaders.toSingleValueMap();
    }

    private GigaChatRequestDto getBodyForGptModel(String systemText, String userText) {
        return GigaChatRequestDto.builder()
                .model("GigaChat")
                .stream(false)
                .update_interval(0)
                .messages(List.of(
                        Message.builder()
                                .role("system")
                                .content(systemText)
                                .build(),
                        Message.builder()
                                .role("user")
                                .content(userText)
                                .build()
                        )
                ).build();
    }
}
