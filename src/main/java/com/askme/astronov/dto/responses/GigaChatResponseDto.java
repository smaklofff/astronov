package com.askme.astronov.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GigaChatResponseDto implements ResponseDto {

    private List<Choice> choices;
    private int created;
    private String model;
    private String object;
    private Usage usage;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Choice {
        private Message message;
        private int index;
        @JsonProperty("finish_reason")
        private String finishReason;

        @Getter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Message {
            private String content;
            private String role;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Usage {
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;
    }
}
