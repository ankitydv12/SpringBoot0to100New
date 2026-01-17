package com.ankit.module2.service;

import com.ankit.module2.entities.EmployeeEntity;
import com.ankit.module2.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // this creat a bean also  because it contain the component Annotation
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeEntity getEmployeeById(Long id)
    {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public EmployeeEntity addEmployee(EmployeeEntity inputemp) {
        return employeeRepository.save(inputemp);
    }
}
