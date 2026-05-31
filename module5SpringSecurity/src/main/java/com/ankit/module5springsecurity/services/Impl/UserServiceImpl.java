package com.ankit.module5springsecurity.services.Impl;

import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.repository.UserRepository;
import com.ankit.module5springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
