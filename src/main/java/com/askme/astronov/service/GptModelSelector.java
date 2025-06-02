package com.askme.astronov.service;

import com.askme.astronov.service.impl.mistral.MistralChatServiceImpl;
//import com.askme.astronov.service.impl.yandexGpt.YandexGptServiceImpl;
import com.askme.astronov.service.impl.gigaChat.GigaChatServiceImpl;
import com.askme.astronov.utils.Enums.GptModelType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GptModelSelector {

    private final GigaChatServiceImpl gigaChatService;
//    private final YandexGptServiceImpl yandexGptService;
    private final MistralChatServiceImpl mistralChatServiceImpl;

    public GptModel getGptModel(GptModelType gptModelType) {
        return switch (gptModelType) {
//            case YANDEX_GPT -> yandexGptService;
//            case MISTRAL ->  mistralChatServiceImpl;
            default ->  gigaChatService;
        };
    }
}
