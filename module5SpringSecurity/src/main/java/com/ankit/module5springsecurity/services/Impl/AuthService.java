package com.ankit.module5springsecurity.services.Impl;

import com.ankit.module5springsecurity.dto.LoginDTO;
import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));
        User user = (User) authentication.getPrincipal();

        assert user != null;
        String token =  jwtService.generateJwtToken(user);
        System.out.println(token);
        return token;

    }
}
