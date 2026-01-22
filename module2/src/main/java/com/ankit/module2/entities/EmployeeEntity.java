package com.ankit.module2.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
/*
 * EmployeeEntity represents a TABLE in the database.
 *
 * Entity layer maps Java class ↔ Database table.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;
    private Double salary;
}
