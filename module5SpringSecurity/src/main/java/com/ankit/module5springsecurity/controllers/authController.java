package com.ankit.module5springsecurity.controllers;

import com.ankit.module5springsecurity.advice.ApiResponse;
import com.ankit.module5springsecurity.dto.LoginDTO;
import com.ankit.module5springsecurity.dto.SignupDTO;
import com.ankit.module5springsecurity.dto.UserDTO;
import com.ankit.module5springsecurity.services.Impl.AuthService;
import com.ankit.module5springsecurity.services.Impl.UserServiceImpl;
import com.ankit.module5springsecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private  final AuthService authService;
    @PostMapping({"/signup", "/singup"})
    public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO signupDTO){
        UserDTO userDTO = userService.signup(signupDTO);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody LoginDTO loginDTO , HttpServletRequest request , HttpServletResponse response)
    {
        String token = authService.login(loginDTO);
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);
        return new ApiResponse<>(token);
    }
}
