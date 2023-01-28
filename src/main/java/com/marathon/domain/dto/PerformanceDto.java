package com.marathon.domain.dto;

import com.marathon.domain.StartStatus;
import java.math.BigDecimal;

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
