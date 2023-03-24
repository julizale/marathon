package com.marathon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "races")
public class Race {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "race_id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "distance")
    private Long distance;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(
            targetEntity = Performance.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "race"
    )
    private List<Performance> performanceList = new ArrayList<>();
}
