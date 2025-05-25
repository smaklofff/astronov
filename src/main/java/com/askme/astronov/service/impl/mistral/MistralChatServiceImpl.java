package com.askme.astronov.service.impl.mistral;

import com.askme.astronov.config.SecretsConfig;
import com.askme.astronov.dto.SurveyRequestDto;
import com.askme.astronov.dto.requests.MistralChatRequestDto;
import com.askme.astronov.dto.requests.MistralChatRequestDto.ChatMessageDto;
import com.askme.astronov.dto.responses.MistralChatResponseDto;
import com.askme.astronov.properties.MistralProperties;
import com.askme.astronov.service.GptModel;
import com.askme.astronov.service.feignClients.mistralGpt.MistralFeignClient;
import com.askme.astronov.utils.ConverterUtil;
import com.askme.astronov.utils.integration.RequestSenderUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MistralChatServiceImpl extends GptModel {

    private final SecretsConfig secretsConfig;
    private final MistralFeignClient mistralFeignClient;
    private final RequestSenderUtil requestSenderUtil;
    private final MistralProperties mistralProperties;


    @Override
    public <T> List<Map<String, T>> getSurvey(SurveyRequestDto surveyRequestDto) {
        return processGptModelResponse(getMistralChatResponseDto(surveyRequestDto)).stream()
                .map(x -> ConverterUtil.convertToObject(x, new TypeReference<Map<String, T>>() {})).toList();
    }

    private List<String> processGptModelResponse(MistralChatResponseDto mistralChatResponseDto) {
        return mistralChatResponseDto.getChoices().stream()
                .map(choice -> extractContentBetweenBraces(choice.getMessage().getContent())).toList();
    }

    private MistralChatResponseDto getMistralChatResponseDto(SurveyRequestDto surveyRequestDto) {
        log.info("Start getting giga chat response");
        return ConverterUtil.convertToObject(
                requestSenderUtil.sendRequest(
                        mistralFeignClient,
                        getBody(surveyRequestDto.getSystemText(), getUserTextForModel(surveyRequestDto)),
                        getHeaders()),
                new TypeReference<>() {}
        );
    }

    private MistralChatRequestDto getBody(String systemText, String userText) {
        return MistralChatRequestDto.builder()
                .model(mistralProperties.model())
                .temperature(mistralProperties.temperature())
                .messages(List.of(
                        ChatMessageDto.builder().role("system").content(systemText).build(),
                        ChatMessageDto.builder().role("user").content(userText).build()))
                .n(mistralProperties.n())
                .build();
    }

    private Map<String, String> getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + secretsConfig.getMistralGptApiKey());
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add("X-Request-ID", UUID.randomUUID().toString());
        return httpHeaders.toSingleValueMap();
    }
}
