package com.marathon.controller;

import com.marathon.domain.dto.weather.WeatherDayDto;
import com.marathon.exception.ValidationException;
import com.marathon.mapper.DateMapper;
import com.marathon.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private DateMapper dateMapper;

    @GetMapping(value = "{date}")
    public ResponseEntity<WeatherDayDto> getWeather(@PathVariable String date) throws ValidationException {
        WeatherDayDto weather = weatherService.getWeatherDay(dateMapper.mapToLocalDate(date));
        return ResponseEntity.ok(weather);
    }
}
