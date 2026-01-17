package com.ankit.module2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
/*
 * EmployeeEntity represents a TABLE in the database.
 *
 * Entity layer maps Java class ↔ Database table.
 */

@Entity
/*
 * @Entity tells JPA:
 * "This class should be mapped to a database table"
 *
 * Without this annotation, JPA will IGNORE the class.
 */

@Table(name = "employees")
/*
 * @Table is optional.
 * Used when table name is different from class name.
 *
 * Here:
 * Class → EmployeeEntity
 * Table → employees
 */
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private Boolean isActive;

    public EmployeeEntity(){}

    public EmployeeEntity(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
