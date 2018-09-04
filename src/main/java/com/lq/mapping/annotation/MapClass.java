package com.lq.mapping.annotation;

import java.lang.annotation.*;

/**
 * Annotation for mapping the class
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MapClass {
    /**
     * Set the class which need to be mapped with the annotated class
     *
     * @return
     */
    String value() default "";
}
