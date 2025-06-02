package com.askme.astronov.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface JamAnnotations {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @interface IncomingRequest {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @interface OutgoingRequest {}
}
