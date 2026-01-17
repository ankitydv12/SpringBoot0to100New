package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeeDTO;
import com.ankit.module2.entities.EmployeeEntity;
import com.ankit.module2.repositories.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
    This Code Show to HTTP request that connect to a database . here dummy data is used just to show
    In this i am not using lambook for EmployeeEntity Management
 */

@RestController
@RequestMapping("/employees") //this will work as main url inside it empId comes
public class EmployeeController {

    /*
        *Injecting the bean of EmployeeRepository through Constructor .
    */
    private final EmployeeRepository employeeRepositoryObj;
    public EmployeeController(EmployeeRepository employeeRepositoryObj) {
        this.employeeRepositoryObj = employeeRepositoryObj;
    }

    @GetMapping(path="/{empId}")
    public EmployeeEntity sendDTO(@PathVariable(name = "empId") Long empID)
    {
        /*
         * findById() returns Optional<EmployeeEntity>
         *
         * Optional is used to avoid NullPointerException.
         *
         * orElse(null) means:
         * - If record exists → return entity
         * - If not → return null
         *
         * INTERVIEW POINT:
         * Returning null is NOT recommended in production.
         * Proper approach: throw custom exception (EmployeeNotFoundException).
         */
       return employeeRepositoryObj.findById(empID).orElse(null); // this return an optional
    }

    //http://localhost:8080/employees?inputid=7&age=21
    @GetMapping
    public List<EmployeeEntity> allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id , Integer age )
    {
        /*
         * findAll() fetches all records from DB.
         *
         * IMPORTANT:
         * In real projects, pagination MUST be used.
         */
        return employeeRepositoryObj.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmp(@RequestBody EmployeeEntity inputemp){
        /*
         * save() performs INSERT if ID is null
         * save() performs UPDATE if ID exists
         *
         * INTERVIEW POINT:
         * save() is not purely INSERT.
         */
        return employeeRepositoryObj.save(inputemp);
    }

    @PutMapping
    public String updateEmpById(){
        return "Hello from the put ";
    }
}
