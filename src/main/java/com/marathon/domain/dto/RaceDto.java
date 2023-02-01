package com.marathon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class RaceDto {

    private long id;
    private String name;
    private long distance;
    private BigDecimal price;
}
