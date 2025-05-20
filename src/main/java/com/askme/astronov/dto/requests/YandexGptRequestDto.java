package com.askme.astronov.dto.requests;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class YandexGptRequestDto {

    private String modelUri;
    private CompletionOptions completionOptions;
    private List<Message> messages;
    private List<Tool> tools;

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompletionOptions {

        private boolean stream;
        private double temperature;
        private String maxTokens;
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        private String role;
        private String text;
        private ToolCallList toolCallList;
        private ToolResultList toolResultList;

        @Getter
        @Setter
        @SuperBuilder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ToolCallList {

            private List<ToolCall> toolCalls;

            @Getter
            @Setter
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class ToolCall {

                private FunctionCall functionCall;

                @Getter
                @Setter
                @SuperBuilder
                @NoArgsConstructor
                @AllArgsConstructor
                public static class FunctionCall {

                    private String name;
                    private Map<String, Object> arguments;
                }
            }
        }

        @Getter
        @Setter
        @SuperBuilder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ToolResultList {

            private List<ToolResult> toolResults;

            @Getter
            @Setter
            @SuperBuilder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class ToolResult {

                private FunctionResult functionResult;

                @Getter
                @Setter
                @SuperBuilder
                @NoArgsConstructor
                @AllArgsConstructor
                public static class FunctionResult {

                    private String name;
                    private String content;
                }
            }
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tool {

        private Function function;

        @Getter
        @Setter
        @SuperBuilder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Function {

            private String name;
            private String description;
            private Map<String, Object> parameters;
        }
    }
}
