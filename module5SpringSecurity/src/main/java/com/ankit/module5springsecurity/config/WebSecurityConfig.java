package com.ankit.module5springsecurity.config;

import com.ankit.module5springsecurity.filters.jwtAuthFilter;
import com.ankit.module5springsecurity.handler.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final jwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers( "/posts", "/error" , "/auth/**","/home.html").permitAll()

                                .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()) // csrf is disabled
                .sessionManagement(sessionconfig->sessionconfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))//session become stateless
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2LoginConfi ->
                        oauth2LoginConfi.failureUrl("/login?error=true") //Handle the failure
                                .successHandler(oAuth2SuccessHandler) //handle the sucess
                );

//                .formLogin(Customizer.withDefaults());

        return  httpSecurity.build();
    }

    //creating inmemory user
//    @Bean
//    public UserDetailsService inMemoryUserDetail(){
//        UserDetails normaluser = User
//                .withUsername("abc")
//                .password(passwordEncoder().encode("abc"))
//                .roles("NORMAL_USER")
//                .build();
//
//        UserDetails admin = User
//                .withUsername("ankit")
//                .password(passwordEncoder().encode("ankit"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails manager = User
//                .withUsername("manager")
//                .password(passwordEncoder().encode("manager"))
//                .roles("MANAGER")
//                .build();
//
//        return new InMemoryUserDetailsManager(normaluser,admin,manager);
//    }

    //password encoder without it spring through error


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
    {
        return config.getAuthenticationManager();
    }
}
