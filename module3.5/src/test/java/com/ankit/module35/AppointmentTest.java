package com.ankit.module35;

import com.ankit.module35.entity.Appointment;
import com.ankit.module35.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class AppointmentTest {
    @Autowired
    private AppointmentService appointmentService;
    @Test
    void appointmentTest(){
        Appointment appointment = Appointment.builder()
                .appointTime(LocalDateTime.of(2018, Month.JANUARY, 1, 23, 59))
                .reason("Feaver")
                .build();
        var updatedAppointment = appointmentService.setAppointment(appointment,2L,3L);
        System.out.println(updatedAppointment);
    }
}
