package com.askme.astronov.utils.integration;

import com.askme.astronov.service.FiegnClients.FiegnClient;
import feign.FeignException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.Map;

public interface RequestSenderUtil {

    @Retryable(
            retryFor = { FeignException.class },
            backoff = @Backoff(delay = 5000),
            maxAttempts = 2
    )
    <T> String sendRequest(T requestBody, Map<String, String> requestHeaders);

    void setFiegnClient(FiegnClient fiegnClient);
}
