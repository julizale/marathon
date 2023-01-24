package com.marathon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "USERS")
public class User {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "BIRTHDATE")
    private Date birthDate;

    @NotNull
    @Column(name = "SEX")
    private Sex sex;

    @Column(name = "CITY")
    private String city;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(mappedBy = "user")
    private Performance performance;
}
