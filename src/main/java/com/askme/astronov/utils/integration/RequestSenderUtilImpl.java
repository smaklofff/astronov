package com.askme.astronov.utils.integration;

import com.askme.astronov.aspects.annotations.OutgoingRequest;
import com.askme.astronov.service.FiegnClients.FiegnClient;
import com.askme.astronov.service.FiegnClients.gigaChat.GigaFiegnClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@Setter
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class RequestSenderUtilImpl implements RequestSenderUtil{

    private FiegnClient fiegnClient;

    @Override
    @OutgoingRequest
    public <T> String sendRequest(T requestBody, Map<String, String> requestHeaders) {
        String response;
        try {
//            if (fiegnClient instanceof GigaFiegnClient) {
//                response = "{\"choices\":[{\"message\":{\"content\":\"{\\\"question\\\": \\\"Какой метод используется для фильтрации элементов в Stream API в Java?\\\", \\\"options\\\": [\\\"filter()\\\", \\\"forEach()\\\", \\\"map()\\\", \\\"reduce()\\\"], \\\"correctOptions\\\": [\\\"filter()\\\"]}\",\"role\":\"assistant\"},\"index\":0,\"finish_reason\":\"stop\"}],\"created\":1728498179,\"model\":\"GigaChat:3.1.25.3\",\"object\":\"chat.completion\",\"usage\":{\"prompt_tokens\":103,\"completion_tokens\":51,\"total_tokens\":154}}";
//            } else {
//                response = "{\"access_token\":\"eyJjdHkiOiJqd3QiLCJlbmMiOiJBMjU2Q0JDLUhTNTEyIiwiYWxnIjoiUlNBLU9BRVAtMjU2In0.wpO-TnmFW1uOprDPB8phOxXV6rqlS5dn6lZwCglEhjtLN3aeV5UpEi3woRwYSylmXCoB4wBTbLpORkOlu5RtkhaLSaKjwG_pqa9neK-gmp7rYsO-CRF022RjqM05JXkWKf-uh1MeLyw-xki0fgmQ-fvdOrWDSLfHKEWZW2AQXTU4JDgIYRpoLfDMch7WDPgbKlyZeP4zohWjynveb1VIgTfXaat4I0nYe57Y-9DldBEeJLd-X0ED4LXkwXGv2KlbJWIBEamo0TaCX6wnjQF6NyWxPMh0Q9g7eITJt5AtqmDO8t74xL1dfrsPXxFpY_VF5Ewjrr1Ci6eFv-54ZphxEQ.TpLTuyablG104ncneJnzuQ.fjRSRWJP16havM2DkGbFZJcVbG-DJjrZkIC2FWzFNRABcf68NO0RKGo0lkYVyExNUtv_GsA-EH0yMKQ0MWwz4xPU-U9oyC1Zc9RvBXSbTEqtTKfpkv-xFI5YELi0YXrfGJDOuYkwcpfi4PyMndd3_BsuRbLv5Mo_z5CvVp82RmS1QrwxVRp-cJveGiDMLHRhwgB6qQKBi7NCvxLGPe2E-qE5N4Ig7wxpZ4-0M_XY-dCh15hofGHp5vWqcrBTba-4k71ZApMaMWrFBgq_lSpdGXfo7DHYgUj6W5ZgMMKcBZCwyA8ym_tDE7dZdHV_YkXoXhFK36NLd7PHps-ySLUZfLHLIslFdTby4v7idukjwQjvw0mwUzKCJt8STmyStKFNHZPRg3URHbcdR8vw5pS_PQXfgao9WNRnrVi2Gevey1gbcrXiBvuU78tqAtbKIO23Ukmv0nhHRjPWQyiVeiEN3yb6Z2i_gFgEedAcTXN_-JFIB4hvFI8EI6CCuy_4l3GCkjquC6X1U1TSyFKeP8Y-poVRjvG5_P60U9_JZ3Y3m7RQLhFmb1V1CZaRjBL2ZrIhaUXe4Tucw5QfHZsQuDu39_iiQCbqVgYdbbMU86bHFr1ZoSxkIRAyj5rZvl3mqZgKl1GSbXUKW1a9-8o5DP2048uMBuuE-NOGP1Y2utqyXBlsoWv7RyAwrQtdwGINof-Ufczn5wV9bY7yucaYWygmxNZMaSDdr9c89aZC0eFNYFY.qkO0lKAcXCeESI007qIAwxeCF3yit-DsO2LMj7sDP7I\",\"expires_at\":1728649134181}";
            response = fiegnClient.sendRequest(requestBody, requestHeaders);
//            }

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
