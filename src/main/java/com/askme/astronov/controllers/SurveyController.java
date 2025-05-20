package com.askme.astronov.controllers;

import com.askme.astronov.dto.SurveyRequestDto;
import com.askme.astronov.utils.Enums.GptModelType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/survey")
public interface SurveyController {

    @PostMapping("/create/{model}")
    <T> ResponseEntity<List<Map<String, T>>> generateSurvey(
            @PathVariable GptModelType model,
            @Valid @RequestBody SurveyRequestDto surveyRequestDto
    );

    @DeleteMapping("/cache/clear/{model}")
    ResponseEntity<Void> clearCache(@PathVariable GptModelType model);

    @PutMapping("/cache/update/{model}")
    <T> ResponseEntity<List<Map<String, T>>> updateGenerateSurvey(
            @PathVariable GptModelType model,
            @Valid @RequestBody SurveyRequestDto surveyRequestDto);

    @DeleteMapping("/cache/delete/{model}")
    ResponseEntity<Void> deleteGenerateSurvey(
            @PathVariable GptModelType model,
            @Valid @RequestBody SurveyRequestDto surveyRequestDto);
}
