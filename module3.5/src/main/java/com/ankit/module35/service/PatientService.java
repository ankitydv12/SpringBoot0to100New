package com.ankit.module35.service;

import com.ankit.module35.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
   // PatientService (PatientRepository patientRepository){this.patientRepository = patientRepository;}

    @Transactional
    public void deletePatient(Long Id){
        patientRepository.findById(Id).orElseThrow();
        patientRepository.deleteById(Id);
    }

}
