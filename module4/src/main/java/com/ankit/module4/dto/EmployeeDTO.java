package com.ankit.module4.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/*
    *DTO stand for Data Transfer Object
    * This is POJO class
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class EmployeeDTO {

    private String name;

    private String email;

    private Integer age;


    private String role;


    private Double salary;


    @CreationTimestamp
    private LocalDate dateOfJoining;


    private Boolean isActive;


    private Integer prime;

}
