package com.askme.astronov.dto.requests;

import lombok.Builder;
import lombok.Data;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
public class MistralChatRequestDto implements RequestDto {

    @NotBlank(message = "Model cannot be empty")
    private String model;
    private double temperature;
    private double topP;
    private int maxTokens;
    private boolean stream;
    private String stop;
    private int randomSeed;
    @NotBlank(message = "Messages cannot be empty")
    private List<ChatMessageDto> messages;
    private ResponseFormatDto responseFormat;
    private List<ToolDto> tools;
    private String toolChoice;
    private double presencePenalty;
    private double frequencyPenalty;
    private int n;
    private boolean safePrompt;

    @Data
    @Builder
    public static class ChatMessageDto {
        private String role;
        private String content;
    }

    @Data
    @Builder
    public static class ResponseFormatDto {
        private String type;
    }

    @Data
    @Builder
    public static class ToolDto {
        private String type;
        private FunctionDto function;

        @Data
        @Builder
        static class FunctionDto {
            private String name;
            private String description;
            private Object parameters;
        }
    }
}
