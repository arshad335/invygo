package com.example.invygo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SCHEDULE_TBL")
@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Schedule implements Serializable {
    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date workDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private User user;

    @Column(nullable = false)
    private Float shiftLength;

}

