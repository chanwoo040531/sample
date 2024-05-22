package me.chnu.sample.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Transactional(readOnly = false)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WriteService {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}