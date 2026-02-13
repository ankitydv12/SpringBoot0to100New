package com.ankit.module35.repository;

import com.ankit.module35.dto.BloodGroupCount;
import com.ankit.module35.dto.CPatientConcrete;
import com.ankit.module35.dto.IPatientInfo;
import com.ankit.module35.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    //Interface Based Projection
    @Query("select p.id as Id, p.name as Name, p.email as Email from Patient p")
    List<IPatientInfo> getPatientInfo();

    //Class Based Projection
    @Query("select new com.ankit.module35.dto.CPatientConcrete(p.id,p.name)"+"from Patient p")
    List<CPatientConcrete> getPatientConcrete();

    //Aggregate Query
    @Query("select  new com.ankit.module35.dto.BloodGroupCount(p.bloodGroup, count(p)) from Patient p group by p.bloodGroup order by count(p) DESC ")
    List<BloodGroupCount> getBloodGroupCount();


    @Modifying //tells jpa repository that this is going to modify the database
    @Transactional // make sure that transaction is fully completed or fully fail
    @Query("UPDATE Patient p set p.name = :name where p.id = :id ")
    int updatePatientNameWithId(@Param("name") String name,@Param("id")Long id);
}
