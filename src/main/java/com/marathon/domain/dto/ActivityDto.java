package com.marathon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ActivityDto {

    private long id;
    private LocalDateTime dateTime;
    private long userId;
    private String log;
}
