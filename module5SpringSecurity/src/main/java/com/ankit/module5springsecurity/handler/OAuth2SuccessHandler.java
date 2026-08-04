package com.ankit.module5springsecurity.handler;

import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.services.Impl.JwtService;
import com.ankit.module5springsecurity.services.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User OAuth2user = (DefaultOAuth2User) authentication.getPrincipal();
        String email = OAuth2user.getAttribute("email");
        log.info(OAuth2user.getAttribute(email));
        //find the user by email
        User user = userService.findByEmail(email);
        if(user==null){
            //creating  a user
           User newuser =  User.builder()
                    .name(OAuth2user.getAttribute("name"))
                    .email(email)
                    .build();
            userService.save(newuser);
        }
        String Accesstoken = jwtService.generateAccessToken(user);
        String RefreshToken = jwtService.generateRefereshToken(user);

        Cookie cookie = new Cookie("RefreshToken", RefreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); //Only in devlopment mode
        response.addCookie(cookie);

        String frontendurl = "http://localhost:8080/home.html?token="+Accesstoken;

        getRedirectStrategy().sendRedirect(request,response,frontendurl);
        // or
        response.sendRedirect(frontendurl);

    }
}
