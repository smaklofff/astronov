package com.askme.astronov.service.feignClients.gigaChat;

import com.askme.astronov.config.FeignGigaConfig;
import com.askme.astronov.service.feignClients.BasicFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "gigaAccessToken",
        url = "https://ngw.devices.sberbank.ru:9443",
        configuration = FeignGigaConfig.class
)
public interface GigaChatAccessTokenFeignClient extends BasicFeignClient {

    @PostMapping("/api/v2/oauth")
    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                                      @RequestHeader Map<String, String> headers);
}
