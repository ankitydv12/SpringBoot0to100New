package com.ankit.module35;

import com.ankit.module35.entity.Insurance;
import com.ankit.module35.entity.Patient;
import com.ankit.module35.service.InsurenceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceTest {
    @Autowired
    public InsurenceService insurenceService;

    @Test
    public void testAssignInsuranceToPatient(){
        Insurance insurance = Insurance.builder()
                .provider("HDFC insurence")
                .policyNumber("HDFC_123")
                .validUntil(LocalDate.of(2030,1,1)).build();
        insurenceService.assignInsurance(1L,insurance);
    }

}
