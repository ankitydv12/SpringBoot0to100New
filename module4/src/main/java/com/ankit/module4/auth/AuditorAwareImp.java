package com.ankit.module4.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String>{
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("Ankit Yadav");
    }
}
