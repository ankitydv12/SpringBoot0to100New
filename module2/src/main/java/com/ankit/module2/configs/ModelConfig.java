package com.ankit.module2.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
 * @Configuration
 *
 * @Configuration is a SPECIALIZED Spring annotation used to define
 * application-level configuration classes.
 *
 * A class annotated with @Configuration is used to declare @Bean methods.
 * These beans are managed by the Spring IoC container.
 *
 *
 * -------------------------
 * WHY @Configuration IS USED
 * -------------------------
 *
 * - To define CUSTOM beans (ModelMapper, RestTemplate, PasswordEncoder, etc.)
 * - To keep configuration logic separate from business logic
 * - To replace old XML-based Spring configuration
 *
 *
 * -------------------------
 * HOW IT WORKS INTERNALLY
 * -------------------------
 *
 * - Spring creates a PROXY of the @Configuration class.
 * - This proxy ensures that:
 *   → Each @Bean method returns a SINGLETON object
 *   → Same bean instance is reused across the application
 *
 * IMPORTANT:
 * Without @Configuration, @Bean methods may create
 * a NEW object every time they are called.
 *
 *
 * -------------------------
 * RELATION WITH @Bean
 * -------------------------
 *
 * @Configuration class CONTAINS @Bean methods.
 *
 * Example use-case:
 * - ModelMapper bean
 * - ObjectMapper bean
 * - Security-related beans
 *
 *
 * -------------------------
 * INTERVIEW-IMPORTANT POINTS
 * -------------------------
 *
 * ✔ @Configuration is detected during component scanning.
 * ✔ It is part of Spring Core, not Spring Boot specific.
 * ✔ Ensures full control over bean lifecycle.
 * ✔ Preferred over XML configuration in modern Spring apps.
 *
 */

@Configuration
public class ModelConfig{
    @Bean
    public ModelMapper getModelMap()
    {
        return new ModelMapper();
    }
}
