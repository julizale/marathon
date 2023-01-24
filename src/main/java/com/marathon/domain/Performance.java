package com.marathon.domain;

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
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
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
