package com.askme.astronov.service.FiegnClients.gigaChat;

import com.askme.astronov.config.FeignGigaConfig;
import com.askme.astronov.service.FiegnClients.FiegnClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "gigaFiegnClient",
        url = "https://gigachat.devices.sberbank.ru/api/v1",
        configuration = FeignGigaConfig.class
)
public interface GigaFiegnClient extends FiegnClient {

    @PostMapping("/chat/completions")
    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                           @RequestHeader Map<String, String> headers);
}
