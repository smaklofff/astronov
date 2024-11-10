package com.askme.astronov.dto.requests;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GigaAccessTokenRequestDto implements RequestDto {
    private String body;
}
