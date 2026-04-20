package com.ankit.module4.config;

import com.ankit.module4.auth.AuditorAwareImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "getAuditorAware")
@Configuration
public class AppConfig {
    @Bean
    ModelMapper getModelMapper(){return new ModelMapper();}
    @Bean
    AuditorAwareImp getAuditorAware(){return  new AuditorAwareImp();}
}
