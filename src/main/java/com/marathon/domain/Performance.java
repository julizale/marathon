package com.marathon.domain;

import com.marathon.domain.enumerated.StartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "PERFORMANCES")
public class Performance {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "RACE_ID")
    private Race race;

    @Column(name = "PAID")
    private boolean paid;

    @Column(name = "BIB_NUMBER")
    private int bibNumber;

    @Column(name = "STATUS")
    private StartStatus status;

    @Column(name = "TIME_GROSS")
    private BigDecimal timeGross;

    @Column(name = "TIME_NET")
    private BigDecimal timeNet;

}
