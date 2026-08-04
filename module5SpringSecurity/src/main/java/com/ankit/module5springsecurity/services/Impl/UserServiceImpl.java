package com.ankit.module5springsecurity.services.Impl;

import com.ankit.module5springsecurity.dto.SignupDTO;
import com.ankit.module5springsecurity.dto.UserDTO;
import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.exception.ResourceNotFoundException;
import com.ankit.module5springsecurity.repository.UserRepository;
import com.ankit.module5springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private  final PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()-> new BadCredentialsException("User not found"));
    }

    public UserDTO signup(@RequestBody SignupDTO signupDTO)
    {
        Optional<User> user = userRepository.findByEmail(signupDTO.getEmail());
        if(user.isPresent())
        {
            throw new BadCredentialsException(signupDTO.getEmail() + "  is already present");
        }

        User tobeCreated = modelMapper.map(signupDTO,User.class);
        tobeCreated.setPassword(passwordEncoder.encode(signupDTO.getPassword()));

        User savedUser =  userRepository.save(tobeCreated);

        return modelMapper.map(savedUser,UserDTO.class);
    }

    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("User not found with id "+userId));
    }

    public User findByEmail(String email) {
         return userRepository.findByEmail(email).orElse(null);
    }

    public void save(User newuser) {
        userRepository.save(newuser);
    }
}
