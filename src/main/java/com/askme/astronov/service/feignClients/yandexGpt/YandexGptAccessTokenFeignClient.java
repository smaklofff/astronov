package com.askme.astronov.service.feignClients.yandexGpt;

import com.askme.astronov.config.FeignYandexConfig;
import com.askme.astronov.service.feignClients.BasicFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "yandexAccessToken",
        url = "https://iam.api.cloud.yandex.net",
        configuration = FeignYandexConfig.class
)
public interface YandexGptAccessTokenFeignClient extends BasicFeignClient {

    @PostMapping("/iam/v1/tokens")
    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                                      @RequestHeader Map<String, String> headers);
}
