package com.ankit.module35.service;

import com.ankit.module35.entity.Appointment;
import com.ankit.module35.entity.Doctor;
import com.ankit.module35.entity.Patient;
import com.ankit.module35.repository.AppointmentRepository;
import com.ankit.module35.repository.DoctorRepository;
import com.ankit.module35.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment setAppointment(Appointment appointment , Long patientId , Long doctorId) {
        Patient  patient  =patientRepository.findById(patientId).orElseThrow();
        Doctor doctor =  doctorRepository.findById(doctorId).orElseThrow();
        appointment.setPatient(patient);
        appointment.setDoctor_id(doctor);
        //appointmentRepository.save(appointment);
        return appointment;
    }
}
