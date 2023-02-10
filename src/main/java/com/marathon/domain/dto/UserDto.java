package com.marathon.domain.dto;

import com.marathon.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Sex sex;
    private String city;
    private String password;
    private long teamId;
    private long performanceId;

    public UserDto(long id, String email, String firstName, String lastName, LocalDate birthDate,
                   Sex sex, String city, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.city = city;
        this.password = password;
    }

}
