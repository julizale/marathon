package com.marathon.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "ACTIVITIES")
public class Activity {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "DATE_TIME")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "LOG")
    private String log;

}
