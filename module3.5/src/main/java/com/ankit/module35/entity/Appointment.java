package com.ankit.module35.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Patient patient;
    private String status;
    @ManyToOne
    @JoinColumn(nullable = false)
    private  Doctor doctor_id;

}
