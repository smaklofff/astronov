package com.askme.astronov.service.FiegnClients.yandexGpt;

import com.askme.astronov.service.FiegnClients.FiegnClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "yandexAccessToken",
        url = "https://iam.api.cloud.yandex.net"
)
public interface YandexGptAccessTokenFiegnClient extends FiegnClient {

    @PostMapping("/iam/v1/tokens")
    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                                      @RequestHeader Map<String, String> headers);
}
