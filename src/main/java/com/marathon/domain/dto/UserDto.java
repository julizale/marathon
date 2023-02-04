package com.marathon.domain.dto;

import com.marathon.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
public class UserDto {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Sex sex;
    private String city;
    private long teamId;
    private long performanceId;

}
