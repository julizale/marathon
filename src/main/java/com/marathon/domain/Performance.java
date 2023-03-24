package com.marathon.domain;

import com.marathon.domain.enumerated.StartStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "performances")
public class Performance {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "performance_id")
    private Long id;

    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "race_id")
    private Race race;

    @Column(name = "paid")
    private boolean paid;

    @Column(name = "bibnumber")
    private int bibNumber;

    @Column(name = "status")
    private StartStatus status;

    @Column(name = "timegross")
    private BigDecimal timeGross;

    @Column(name = "timenet")
    private BigDecimal timeNet;

}
