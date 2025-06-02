package com.askme.astronov.utils.integration;

import com.askme.astronov.service.feignClients.BasicFeignClient;
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
    <T> String sendRequest(BasicFeignClient basicFeignClient, T requestBody, Map<String, String> requestHeaders);
}
