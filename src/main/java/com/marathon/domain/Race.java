package com.marathon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "RACES")
public class Race {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "DISTANCE")
    private long distance;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToMany(
            targetEntity = Performance.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "race"
    )
    private List<Performance> performanceList = new ArrayList<>();
}
