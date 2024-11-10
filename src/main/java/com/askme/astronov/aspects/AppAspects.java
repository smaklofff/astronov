package com.askme.astronov.aspects;

import com.askme.astronov.dto.requests.RequestDto;
import com.askme.astronov.service.MonitoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class AppAspects {

    private final MonitoringService monitoring;

//    @Order(1)
    @Around(value = "@annotation(com.askme.astronov.aspects.annotations.OutgoingRequest)")
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("Adding log info in monitoring system");
        Object[] args = joinPoint.getArgs();

        Object body = null, headers = null;
        for (Object arg : args) {
            if (arg instanceof RequestDto) {
                body = arg;
            } else if (arg instanceof Map<?,?>) {
                headers = arg;
            } else if (arg instanceof HttpEntity<?>) {
                headers = ((HttpEntity<?>) arg).getHeaders();
                body = ((HttpEntity<?>) arg).getBody();
            }
        }

        monitoring.sendingRequest(headers, body);
        Object result = null;
        try {
            result = joinPoint.proceed(args);
        } catch (Throwable e) {
            monitoring.receivedBadResponse(e.getMessage());
        }
        monitoring.receivedSuccessfulResponse(result);
        return result;
    }
}
