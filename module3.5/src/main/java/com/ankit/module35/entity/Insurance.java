package com.ankit.module35.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Insurance {
    @Id
    private Long Id;

    @Column(nullable = false , length = 100 , unique = true)
    private String policyNumber;

    @Column(nullable = false , length = 100)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "insurance") // value of mappedBy same as the insurance field of the Patient
    private Patient patient; //inverse Side
}
