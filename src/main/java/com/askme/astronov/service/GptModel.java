package com.askme.astronov.service;

import com.askme.astronov.dto.SurveyRequestDto;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GptModel {

    public abstract <T> List<Map<String, T>> getSurvey(SurveyRequestDto surveyRequestDto);

    protected String extractContentBetweenBraces(String input) {
        Pattern pattern = Pattern.compile("\\{.*?}", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        return matcher.find()? matcher.group(): "";
    }

    protected String getUserTextForModel(SurveyRequestDto surveyRequestDto) {
        return surveyRequestDto.getUserText()
                + "Ответ представь в json формате следующего вида: "
                + surveyRequestDto.getFormat();
    }
}
