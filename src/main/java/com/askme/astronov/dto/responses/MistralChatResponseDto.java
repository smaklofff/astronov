package com.askme.astronov.dto.responses;

import lombok.Data;
import java.util.List;

@Data
public class MistralChatResponseDto implements ResponseDto {

    private String id;
    private String object;
    private String model;
    private UsageDto usage;
    private long created;
    private List<ChoiceDto> choices;

    @Data
    public static class UsageDto {

        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
    }

    @Data
    public static class ChoiceDto {

        private int index;
        private MessageDto message;
        private String finishReason;

        @Data
        public static class MessageDto {

            private String content;
            private List<ToolCallDto> toolCalls;
            private boolean prefix;
            private String role;

            @Data
            public static class ToolCallDto {

                private String id;
                private String type;
                private FunctionDto function;

                @Data
                public static class FunctionDto {

                    private String name;
                    private Object arguments;
                }
            }
        }
    }
}