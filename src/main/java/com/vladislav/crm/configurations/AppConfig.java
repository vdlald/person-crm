package com.vladislav.crm.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class AppConfig {

    @Bean
    DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ISO_DATE_TIME;
    }
}
