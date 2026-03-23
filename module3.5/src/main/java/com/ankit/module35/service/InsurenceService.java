package com.ankit.module35.service;

import com.ankit.module35.entity.Insurance;
import com.ankit.module35.entity.Patient;
import com.ankit.module35.repository.InsuranceRepository;
import com.ankit.module35.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsurenceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public void assignInsurance(Long Id, Insurance insurance){
        Patient patient = patientRepository.findById(Id).orElseThrow();
        patient.setInsurance(insurance);//the patient instance is automatically created by hibernate it perform the transaction and after that check the dirty commit to synchronise the db it create the insurance instance
        insurance.setPatient(patient);
    }
    @Transactional
    // first remove the existing insureance and then add the new one
    public void updateInsurance(Long Id, Insurance insurance){
        Patient patient = patientRepository.findById(Id).orElseThrow();
        patient.setInsurance(insurance);//the patient instance is automatically created by hibernate it perform the transaction and after that check the dirty commit to synchronise the db it create the insurance instance
        insurance.setPatient(patient);
    }

    @Transactional
    public Patient removeInsurance(Long Id){
        Patient patient = patientRepository.findById(Id).orElseThrow();
        patient.setInsurance(null);//the patient instance is automatically created by hibernate it perform the transaction and after that check the dirty commit to synchronise the db it create the insurance instance
        return patient;
    }


}
