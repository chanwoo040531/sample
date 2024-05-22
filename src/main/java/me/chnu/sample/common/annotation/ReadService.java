package me.chnu.sample.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Transactional(readOnly = true)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ReadService {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}