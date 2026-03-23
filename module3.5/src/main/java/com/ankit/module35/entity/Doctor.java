package com.ankit.module35.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 200, nullable = false)
    private String specialization;

    @Column(nullable = false,unique = true,length = 200)
    private String email;
    private LocalDateTime created_at;
    @OneToMany(mappedBy = "doctor_id", cascade = CascadeType.REMOVE)
    private Set<Appointment> appointments= new HashSet<>();
}
