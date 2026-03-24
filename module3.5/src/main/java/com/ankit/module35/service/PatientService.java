package com.ankit.module35.service;

import com.ankit.module35.entity.Patient;
import com.ankit.module35.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional
    public List<Patient> getAllPatients()
    {
    //    List<Patient> patientList = patientRepository.findAll(); // this lead to a N+1 query problem to resolve this we have to use custom query
          List<Patient> patientList = patientRepository.getAllPatients();
          return patientList;
    }

}
