package com.pclogo.demo.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class LogConfig {
    public static final Logger LOG = LoggerFactory.getLogger(LogConfig.class);

//    @Bean
//    public Person logMethod() {
//        LOG.info("==========print log==========");
//        return new Person();
//    }
}
