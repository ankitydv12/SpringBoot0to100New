package com.ankit.module35.entity;


import com.ankit.module35.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
}
