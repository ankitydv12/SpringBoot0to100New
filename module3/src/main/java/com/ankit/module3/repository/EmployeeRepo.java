package com.ankit.module3.repository;


import com.ankit.module3.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long> {

    EmployeeEntity findByName(String ankit);


    List<EmployeeEntity> findByNameLike(String s);

    EmployeeEntity findByEmail(String s);

    EmployeeEntity findByNameAndAge(String ankit, Integer i);

    EmployeeEntity findByNameOrEmail(String name, String email);

    List<EmployeeEntity> findByAgeGreaterThan(int i);

    List<EmployeeEntity> findByAgeBetween(int i, int i1);

    List<EmployeeEntity> findByIsActiveTrue();


    Optional<EmployeeEntity> findByEmailNull();

    List<Optional<EmployeeEntity>> findByEmailNotNull();

    List<EmployeeEntity> findByNameContaining(String an);

    List<EmployeeEntity> findByNameStartingWith(String an);

    List<EmployeeEntity> findByAgeIn(List<Integer> integers);

    List<EmployeeEntity> findByOrderBySalaryAsc();

    List<EmployeeEntity> findByOrderBySalaryDesc();

    List<EmployeeEntity> findByIsActiveTrueOrderBySalaryDesc();

    Integer countByIsActiveTrue();

    List<EmployeeEntity> existsByEmail(String mail);

    void deleteByIsActiveFalse();


    List<EmployeeEntity> findTop3ByOrderBySalaryDesc();

    EmployeeEntity findFirstByOrderByJoiningDateAsc();

    List<EmployeeEntity> findDistinctByEmail();

}