package com.marathon.domain.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    @JsonProperty("days")
    private List<WeatherDayDto> days;

}