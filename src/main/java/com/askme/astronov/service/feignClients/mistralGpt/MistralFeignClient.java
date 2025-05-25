package com.askme.astronov.service.feignClients.mistralGpt;

import com.askme.astronov.service.feignClients.BasicFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(
        name = "mistralFeignClient",
        url = "https://api.mistral.ai"
)
public interface MistralFeignClient extends BasicFeignClient {

    @PostMapping("/v1/chat/completions")
    <T> String sendRequestToAdjSystem(@RequestBody T requestDto,
                                      @RequestHeader Map<String, String> headers);
}
