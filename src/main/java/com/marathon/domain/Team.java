package com.marathon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "teams")
public class Team {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            targetEntity = User.class,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "team"
    )
    private List<User> userList;
}
