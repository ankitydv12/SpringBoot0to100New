package com.ankit.module35.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 200, nullable = false)
    String name;
    @Column(length = 200, nullable = false)
    String specialization;

    @Column(length = 200, nullable = false,unique = true)
    String email;

    @CreationTimestamp
    LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(nullable = false)
    private Doctor head_doctor;

    @ManyToMany
    private Set<Doctor> doctors = new HashSet<>(); // creation of the HashSet other wise hibernate throw an error when it go add data
}
