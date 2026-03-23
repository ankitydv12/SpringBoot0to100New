package com.ankit.module35.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
    @JsonIgnore
    private Patient patient;
    private String status;
    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private  Doctor doctor_id;

}
