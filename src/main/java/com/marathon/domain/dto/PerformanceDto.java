package com.marathon.domain.dto;

import com.marathon.domain.enumerated.StartStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class PerformanceDto {

    private long id;
    private long userId;
    private long raceId;
    private boolean paid;
    private int bibNumber;
    private StartStatus status;
    private BigDecimal timeGross;
    private BigDecimal timeNet;
}
