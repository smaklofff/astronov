package com.askme.astronov.service.FiegnClients;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public interface FiegnClient {

    default <T> String sendRequest(@RequestBody T requestDto,
                        @RequestHeader Map<String, String> headers) {
        return sendRequestToAdjSystem(requestDto, headers);
    }

    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                                      @RequestHeader Map<String, String> headers);
}
