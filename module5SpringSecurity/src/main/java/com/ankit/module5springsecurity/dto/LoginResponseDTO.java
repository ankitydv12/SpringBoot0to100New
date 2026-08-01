package com.ankit.module5springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO{
    private Long id;
    private String AccessToken;
    private String RefreshToken;
}