package com.marathon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@OpenAPIDefinition
@SpringBootApplication
public class MarathonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarathonApplication.class, args);
    }

}
