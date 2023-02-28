package com.marathon.service;

import com.marathon.config.ApiConfig;
import com.marathon.domain.dto.weather.WeatherDayDto;
import com.marathon.domain.dto.weather.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ApiConfig apiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    public WeatherDayDto getWeatherDay(LocalDate date) {
        LOGGER.info("Sending request to weather api for weather " + date);
        try {
            URI url = buildUrl(date);
            LOGGER.info("Built url :\n" + url);
            WeatherDto weatherResponse = restTemplate.getForObject(url, WeatherDto.class);
            LOGGER.info("Received: " + weatherResponse);
            return weatherResponse != null ? weatherResponse.getDays().get(0) : null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new WeatherDayDto();
        }
    }

    private URI buildUrl(LocalDate date) {
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getWeatherUrl() + date + "/" + date)
                .queryParam("unitGroup", "metric")
                .queryParam("elements", "datetime,temp,feelslike,precip,preciptype,windspeed")
                .queryParam("include", "days")
                .queryParam("key", apiConfig.getWeatherKey())
                .queryParam("contentType", "json")
                .build()
                .encode()
                .toUri();
    }
}
