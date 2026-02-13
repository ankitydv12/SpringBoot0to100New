package com.ankit.module35.repository;

import com.ankit.module35.dto.CPatientConcrete;
import com.ankit.module35.dto.IPatientInfo;
import com.ankit.module35.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    //Interface Based Projection
    @Query("select p.id as Id, p.name as Name, p.email as Email from Patient p")
    List<IPatientInfo> getPatientInfo();

    //Class Based Projection
    @Query("select new com.ankit.module35.dto.CPatientConcrete(p.id,p.name)"+"from Patient p")
    List<CPatientConcrete> getPatientConcrete();
}
