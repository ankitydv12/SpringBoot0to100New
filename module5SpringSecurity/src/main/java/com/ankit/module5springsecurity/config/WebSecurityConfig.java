package com.ankit.module5springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/posts").permitAll() // request posts will be bypass from the authentication
                                .requestMatchers("/post/**").hasAnyRole("ADMIN","MANAGER")
                                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        return  httpSecurity.build();
    }

    //creating inmemory user
    @Bean
    public UserDetailsService inmemmoryUserDetail(){
        UserDetails normaluser = User
                .withUsername("abc")
                .password(passwordEncoder().encode("abc"))
                .roles("NORMAL_USER")
                .build();

        UserDetails admin = User
                .withUsername("ankit")
                .password(passwordEncoder().encode("ankit"))
                .roles("ADMIN")
                .build();

        UserDetails manager = User
                .withUsername("manager")
                .password(passwordEncoder().encode("manager"))
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(normaluser,admin,manager);
    }

    //password encoder without it spring through error
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
