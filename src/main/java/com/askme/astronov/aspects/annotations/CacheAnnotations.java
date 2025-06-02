package com.askme.astronov.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface CacheAnnotations {

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Cacheable {
        String value() default "";

        String key() default "";

        String keyGenerator() default "";
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface CachePut {
        String value() default "";

        String key() default "";

        String keyGenerator() default "";
    }

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface CacheEvict {
        String value() default "";

        String key() default "";

        String keyGenerator() default "";

        boolean allEntries() default false;
    }
}
