package com.askme.astronov.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class YandexGptResponseDto implements ResponseDto {

    private Result result;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {

        private List<Alternative> alternatives;
        private Usage usage;
        private String modelVersion;

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Alternative {

            private Message message;
            private Status status;

            @Getter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Message {

                private String role;
                private String text;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private ToolCallList toolCallList;
                @JsonInclude(JsonInclude.Include.NON_NULL)
                private ToolResultList toolResultList;

                @Getter
                @Setter
                @NoArgsConstructor
                @AllArgsConstructor
                public static class ToolCallList {

                    private List<ToolCall> toolCalls;

                    @Getter
                    @Setter
                    @NoArgsConstructor
                    @AllArgsConstructor
                    public static class ToolCall {

                        private FunctionCall functionCall;

                        @Getter
                        @Setter
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
                @NoArgsConstructor
                @AllArgsConstructor
                public static class ToolResultList {

                    private List<ToolResult> toolResults;

                    @Getter
                    @Setter
                    @NoArgsConstructor
                    @AllArgsConstructor
                    public static class ToolResult {

                        private FunctionResult functionResult;

                        @Getter
                        @Setter
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
            public enum Status {
                ALTERNATIVE_STATUS_UNSPECIFIED("Unspecified generation status."),
                ALTERNATIVE_STATUS_PARTIAL(" Partially generated alternative."),
                ALTERNATIVE_STATUS_TRUNCATED_FINAL("Incomplete final alternative resulting from reaching the maximum allowed number of tokens."),
                ALTERNATIVE_STATUS_FINAL("Final alternative generated without running into any limits."),
                ALTERNATIVE_STATUS_CONTENT_FILTER("Potentially sensitive content in the prompt or generated response."),
                ALTERNATIVE_STATUS_TOOL_CALLS("Tools were invoked during the completion generation.");

                private final String value;

                Status(String value) {
                    this.value = value;
                }
            }

        }

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Usage {

            private String inputTextTokens;
            private String completionTokens;
            private String totalTokens;
        }
    }
}
