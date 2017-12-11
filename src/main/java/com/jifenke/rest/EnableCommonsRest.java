package com.jifenke.rest;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author ZM.Wang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({CommonsRestConfiguration.class})
public @interface EnableCommonsRest {
}
