package com.ankit.module5springsecurity.services.Impl;

import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.exception.ResourceNotFoundException;
import com.ankit.module5springsecurity.repository.UserRepository;
import com.ankit.module5springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
//@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
