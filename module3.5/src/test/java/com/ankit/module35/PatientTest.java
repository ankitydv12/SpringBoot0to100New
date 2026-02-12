package com.ankit.module35;

import com.ankit.module35.dto.IPatientInfo;
import com.ankit.module35.entity.Patient;
import com.ankit.module35.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientTest {
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient(){
    List<IPatientInfo> paitentList = patientRepository.getPatientInfo();
    for (IPatientInfo patient : paitentList){
        System.out.println(patient);
    }
    }
}
