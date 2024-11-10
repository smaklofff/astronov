package com.askme.astronov.controllers;


import com.askme.astronov.dto.SurveyRequestDto;
import com.askme.astronov.service.GptModelSelector;
import com.askme.astronov.utils.Enums.GptModelType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SurveyControllerImpl implements SurveyController {

    private final GptModelSelector gptModelSelector;

    @Override
    public <T> ResponseEntity<List<Map<String, T>>> generateSurvey(
            @PathVariable GptModelType model,
            @Valid @RequestBody SurveyRequestDto surveyRequestDto) {
        return ResponseEntity.ok(gptModelSelector.getGptModel(model).getSurvey(surveyRequestDto));
    }

}
