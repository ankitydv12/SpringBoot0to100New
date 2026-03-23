package com.ankit.module35;

import com.ankit.module35.service.DoctorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DoctorTest {

    @Autowired
    DoctorService doctorService;
    @Test
    public void removeDoc()
    {
        doctorService.deleteDoctorById(Long.valueOf(3));
    }
}
