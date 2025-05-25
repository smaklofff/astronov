package com.askme.astronov.service.feignClients.gigaChat;

import com.askme.astronov.config.FeignGigaConfig;
import com.askme.astronov.service.feignClients.BasicFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "gigaFeignClient",
        url = "https://gigachat.devices.sberbank.ru/api/v1",
        configuration = FeignGigaConfig.class
)
public interface GigaGptFeignClient extends BasicFeignClient {

    @PostMapping("/chat/completions")
    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                           @RequestHeader Map<String, String> headers);
}
