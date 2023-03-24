package com.marathon.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marathon.domain.enumerated.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "users")
public class User {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "password")
    private String password;

    @Column(name = "sex")
    private Sex sex;

    @Column(name = "city")
    private String city;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(mappedBy = "user")
    private Performance performance;

    @OneToMany(
            targetEntity = Activity.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<Activity> activityList;

    @JsonIgnore
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER)
    private Set<Authority> authorities;

    public User(Long id, String email, String firstName, String lastName, LocalDate birthDate, String password, Sex sex, String city, Team team, Performance performance, List<Activity> activityList) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.password = password;
        this.sex = sex;
        this.city = city;
        this.team = team;
        this.performance = performance;
        this.activityList = activityList;
    }
}
