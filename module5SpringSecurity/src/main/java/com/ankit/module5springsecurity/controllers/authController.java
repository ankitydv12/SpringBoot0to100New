package com.ankit.module5springsecurity.controllers;

import com.ankit.module5springsecurity.dto.SignupDTO;
import com.ankit.module5springsecurity.dto.UserDTO;
import com.ankit.module5springsecurity.services.Impl.UserServiceImpl;
import com.ankit.module5springsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class authController {
    private final UserServiceImpl userService;
    @PostMapping({"/signup", "/singup"})
    public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO signupDTO){
        UserDTO userDTO = userService.signup(signupDTO);
        return ResponseEntity.ok(userDTO);
    }
}
