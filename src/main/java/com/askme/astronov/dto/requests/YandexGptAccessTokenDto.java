package com.askme.astronov.dto.requests;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class YandexGptAccessTokenDto implements RequestDto {
    private String yandexPassportOauthToken;
}
