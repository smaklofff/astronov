package com.askme.astronov.aspects;

import com.askme.astronov.aspects.annotations.CacheAnnotations.Cacheable;
import com.askme.astronov.aspects.annotations.CacheAnnotations.CachePut;
import com.askme.astronov.aspects.annotations.CacheAnnotations.CacheEvict;
import com.askme.astronov.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;


@Aspect
@Component
@RequiredArgsConstructor
public class CacheAspect {

    private final ExpressionParser parser = new SpelExpressionParser();
    private final CacheService cacheService;

    @Around(value = "@annotation(cacheable)")
    public Object cacheable(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        EvaluationContext context = getContext(joinPoint);
        String cacheKey = parser.parseExpression(cacheable.key()).getValue(context, String.class);
        Object result = cacheService.get(cacheable.value(), cacheKey);
        if (result == null) {
            result = joinPoint.proceed();
            cacheService.put(cacheable.value(), cacheKey, result);
        }
        return result;
    }

    @Around(value = "@annotation(cachePut)")
    public Object cacheable(ProceedingJoinPoint joinPoint, CachePut cachePut) throws Throwable {
        EvaluationContext context = getContext(joinPoint);
        String cacheKey = parser.parseExpression(cachePut.key()).getValue(context, String.class);
        Object result = joinPoint.proceed();
        cacheService.update(cachePut.value(), cacheKey, result);
        return result;
    }

    @Before(value = "@annotation(cacheEvict)")
    public void cacheable(JoinPoint joinPoint, CacheEvict cacheEvict) throws Throwable {
        EvaluationContext context = getContext(joinPoint);
        String cacheKey = parser.parseExpression(cacheEvict.key()).getValue(context, String.class);
        cacheService.remove(cacheEvict.value(), cacheKey, cacheEvict.allEntries());
    }

    private EvaluationContext getContext(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        return context;
    }
}
