package com.marathon.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ApiConfig {

    @Value("${postalCodeUrl}")
    private String postalCodeUrl;

    @Value("${weatherUrl}")
    private String weatherUrl;

    @Value("${weatherKey}")
    private String weatherKey;
}
