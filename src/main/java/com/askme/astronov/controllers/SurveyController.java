package com.askme.astronov.controllers;

import com.askme.astronov.dto.SurveyRequestDto;
import com.askme.astronov.utils.Enums.GptModelType;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/generateSurvey")
public interface SurveyController {

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Success response to user"),
            @ApiResponse(responseCode = "200",
                    description = "Success response to user"),
            @ApiResponse(responseCode = "200",
                    description = "Success response to user"),
        }
    )
    @PostMapping("/{model}")
    <T> ResponseEntity<List<Map<String, T>>> generateSurvey(
            @PathVariable GptModelType model,
            @Valid @RequestBody SurveyRequestDto surveyRequestDto
    );

}
