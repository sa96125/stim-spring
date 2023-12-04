package com.sa96125.stim.common.api.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotLogging {
    // 이 애노테이션은 메서드에 적용될 때 로깅을 수행하지 않도록 지정
}