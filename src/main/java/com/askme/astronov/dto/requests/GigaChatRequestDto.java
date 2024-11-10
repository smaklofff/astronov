package com.askme.astronov.dto.requests;

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
    private int update_interval;
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
