package com.askme.astronov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LogEventDto {

    private Long id;
    private LocalDateTime timestamp;
    private String event;
    private String message;
}
