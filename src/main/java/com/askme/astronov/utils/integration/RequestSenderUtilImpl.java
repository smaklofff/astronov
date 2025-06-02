package com.askme.astronov.utils.integration;

import com.askme.astronov.service.feignClients.BasicFeignClient;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@Component
public class RequestSenderUtilImpl implements RequestSenderUtil {

    @Override
    public <T> String sendRequest(
            BasicFeignClient basicFeignClient,
            T requestBody,
            Map<String, String> requestHeaders) {
        String response;
        try {
            response = basicFeignClient.sendRequest(requestBody, requestHeaders);
        } catch (FeignException e) {
            log.error("Error sending request", e);
            throw e;
        }
        return response;
    }

    @Recover
    private String recoverRequest(FeignException e) {
        log.error("Error sending request. The maximum number of attempts has been exceeded", e);
        return String.format("{\"Error\": \"Error sending request: %s\"}", e.getMessage());
    }
}
