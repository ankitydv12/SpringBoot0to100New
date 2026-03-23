package com.ankit.module35.service;

import com.ankit.module35.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional
    public void deleteDoctorById(Long id){
        doctorRepository.findById(id).orElseThrow();
        doctorRepository.deleteById(id);
    }
}
