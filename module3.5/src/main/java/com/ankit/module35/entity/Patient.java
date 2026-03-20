package com.ankit.module35.entity;


import com.ankit.module35.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data //--> @Getter , @Setter , @ToString , @EqualsAndHashCode , @RequiredArgsConstructor
@Entity
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private BloodGroupType bloodGroup;

    private String gender;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //mapping to Insurence
    @OneToOne
    @JoinColumn(name = "patient_insurence",unique = true) // insurance is join col and its behavior can be change using @JoinColumn()
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointment; //inverse side
}
