package com.marathon.service;

import com.marathon.config.ApiConfig;
import com.marathon.domain.postal.PostalApiAnswer;
import com.marathon.exception.NotValidCodeException;
import com.marathon.validator.PostalCodeValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostalCodeService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private PostalCodeValidator postalCodeValidator;
    private final ApiConfig apiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(PostalCodeService.class);

    public String getCity(String postalCode) throws NotValidCodeException {
        if (!postalCodeValidator.validatePostalCode(postalCode)) {
            throw new NotValidCodeException();
        }
        LOGGER.info("Sending request to postal code api for code " + postalCode);
        try {
            PostalApiAnswer[] postalResponse = restTemplate.getForObject(apiConfig.getPostalCodeUrl() + postalCode,
                    PostalApiAnswer[].class);
            String miejscowosc = Objects.requireNonNull(postalResponse)[0].getMiejscowosc();
            LOGGER.info("Received: " + miejscowosc);
            return miejscowosc;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return "";
        }
    }
}
