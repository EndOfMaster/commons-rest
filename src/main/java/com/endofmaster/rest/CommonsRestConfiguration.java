package com.endofmaster.rest;

import com.endofmaster.rest.exceptionhandler.DefaultErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZM.Wang
 */
@Configuration
public class CommonsRestConfiguration {
    @Bean
    public DefaultErrorController defaultErrorController() {
        return new DefaultErrorController();
    }
}
