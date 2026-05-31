package com.ankit.module5springsecurity.services;

import com.ankit.module5springsecurity.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getByEmail(String email);
}
