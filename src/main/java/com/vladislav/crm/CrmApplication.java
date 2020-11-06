package com.vladislav.crm;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.BufferedInputStream;
import java.util.Objects;

@EnableJpaAuditing
@SpringBootApplication
public class CrmApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(new String(
                new BufferedInputStream(Objects.requireNonNull(
                        CrmApplication.class.getClassLoader().getResourceAsStream("logo"))).readAllBytes()));
    }
}
