package com.askme.astronov.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GigaChatRequestDto implements RequestDto {

    private String model;
    private boolean stream;
    @JsonProperty("update_interval")
    private int updateInterval;
    private List<Message> messages;

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {

        private String role;
        private String content;
    }
}
