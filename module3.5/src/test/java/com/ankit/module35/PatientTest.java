package com.ankit.module35;

import com.ankit.module35.dto.BloodGroupCount;
import com.ankit.module35.dto.CPatientConcrete;
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

    @Test
    public void ClassbasedProjection(){
        List<CPatientConcrete> cPatientConcretes = patientRepository.getPatientConcrete();
        cPatientConcretes.forEach(cPatientConcrete -> {
            System.out.println(cPatientConcrete);
        });
    }

    @Test
    public void AgrregateQueryProjectio()
    {
        List<BloodGroupCount> bloodGroupCounts = patientRepository.getBloodGroupCount();
        bloodGroupCounts.forEach(bloodGroupCount -> {
            System.out.println(bloodGroupCount);
        });
    }


    @Test
    public void UpdateQuery()
    {
        int res = patientRepository.updatePatientNameWithId("Ankit Yadav",Long.valueOf(4));
        System.out.println(res);
    }


}
