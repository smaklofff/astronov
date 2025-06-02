package com.askme.astronov.service;


import com.askme.astronov.repositories.dao.LogEventRepository;
import com.askme.astronov.repositories.entity.LogEvent;
import com.askme.astronov.utils.ConverterUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.askme.astronov.utils.Enums.EventType.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MonitoringService {

    private final LogEventRepository logEventRepository;

    public <H, B> void receivedIncomingRequest(H headers, B body) {
        String message = getMessage(headers, body);
        logEventRepository.save(new LogEvent(LocalDateTime.now(), RECEIVED_INCOMING_REQUEST, message));
        log.info("Event RECEIVED_INCOMING_REQUEST saved in monitoring with message: {} request", message);
    }

    public <B> void responseToIncomingRequest(B body) {
        String message = getMessage(body);
        logEventRepository.save(new LogEvent(LocalDateTime.now(), RESPONSE_TO_INCOMING_REQUEST, message));
        log.info("Event RESPONSE_TO_INCOMING_REQUEST saved in monitoring with message: {} request", message);
    }

    public <H, B> void sendingRequest(H headers, B body) {
        String message = getMessage(headers, body);
        logEventRepository.save(new LogEvent(LocalDateTime.now(), SENDING_OUTGOING_REQUEST, message));
        log.info("Event SENDING_REQUEST saved in monitoring with message: {} request", message);
    }

    public <B> void receivedBadResponse(B body) {
        String message = getMessage(body);
        logEventRepository.save(new LogEvent(LocalDateTime.now(), RECEIVED_BAD_REQUEST, message));
        log.info("Event RECEIVED_BAD_REQUEST saved in monitoring with message: {} request", message);
    }

    public <B> void receivedSuccessfulResponse(B body) {
        String message = getMessage(body);
        logEventRepository.save(new LogEvent(LocalDateTime.now(), RECEIVED_SUCCESSFUL_REQUEST, message));
        log.info("Event RECEIVED_SUCCESSFUL_REQUEST saved in monitoring with message: {} request", message);
    }

    private <H, B> String getMessage(H headers, B body) {
        return String.format("Body: %s Header: %s", ConverterUtil.convertToString(body),
                ConverterUtil.convertToString(headers));
    }
    private <B> String getMessage(B body) {
        return String.format("Body: %s", ConverterUtil.convertToString(body));
    }
}
