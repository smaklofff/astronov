package com.askme.astronov.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ConverterUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertToObject(String body, TypeReference<T> clazz) {
        T result = null;
        try {
            result = objectMapper.readValue(body, clazz);
        } catch (JsonProcessingException e) {
            log.error("Error while converting object", e);
        }
        return result;
    }

    public static <F, T> T convertObjectToObject(F body, TypeReference<T> clazz) {
        T result = null;
        try {
            result = objectMapper.convertValue(body, clazz);
        } catch (IllegalArgumentException e) {
            log.error("Error while converting object", e);
        }
        return result;
    }

    public static <T> String convertToString(T body) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.error("Error while converting object", e);
        }
        return result;
    }
}
