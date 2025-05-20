package com.askme.astronov.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequestDto {

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 2, max = 255, message = "SystemText must be between 2 and 255 characters")
    private String systemText;

    @NotBlank(message = "This field cannot be empty")
    @Size(min = 2, max = 255, message = "UserText must be between 2 and 255 characters")
    private String userText;

    @Size(min = 2, max = 10000, message = "Format must be between 2 and 1000 characters")
    @NotBlank(message = "This field cannot be empty")
    private String format;
}
