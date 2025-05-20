package com.askme.astronov.service;

import com.askme.astronov.dto.SurveyRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public abstract class GptModel {

    @Cacheable(value = "survey", key = "#surveyRequestDto.toString()", unless = "#result == null || #result.isEmpty()")
    public abstract <T> List<Map<String, T>> getSurvey(SurveyRequestDto surveyRequestDto);

    // Метод для обновления кэша
    @CachePut(value = "survey",
            key = "#surveyRequestDto.toString()",
            unless = "#result == null || #result.isEmpty()")
    public <T> List<Map<String, T>> updateCache(SurveyRequestDto surveyRequestDto) {
        return getSurvey(surveyRequestDto);
    }

    // Метод для удаления конкретного значения из кэша
    @CacheEvict(value = "survey",
            key = "#surveyRequestDto.toString()")
    public void deleteSurveyFromCache(SurveyRequestDto surveyRequestDto) {
        log.info("Evicting cache for request: {}", surveyRequestDto);
    }

    // Метод для очистки всего кэша
    @CacheEvict(value = "survey", allEntries = true)
    public void clearCache() {
        log.info("Clearing entire cache for 'survey'");
    }

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
