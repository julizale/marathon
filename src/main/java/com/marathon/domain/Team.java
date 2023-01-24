package com.marathon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "TEAMS")
public class Team {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = User.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "team"
    )
    private List<User> userList;
}
