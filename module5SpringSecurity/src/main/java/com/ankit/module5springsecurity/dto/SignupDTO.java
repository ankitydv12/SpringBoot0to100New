package com.ankit.module5springsecurity.dto;

import lombok.Data;

@Data
public class SignupDTO {
    private String email;
    private String password;
    private String name;
}
