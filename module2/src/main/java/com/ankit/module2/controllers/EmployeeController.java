package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeeDTO;
import com.ankit.module2.entities.EmployeeEntity;
import com.ankit.module2.repositories.EmployeeRepository;
import com.ankit.module2.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
    Service layer is implemented and now it handle all the bussiness logic
    Controller have the bean of the Service layer which call the function of the EmployeeService
    The Service Layer or EmployeeService have the Repositotry layer which handle db  query and connect to database
    The Problem in this code:
        We have to return EmployeeDTO from service layer but we are returing Employee Entity .
        We need to convert into the EmployeeDTO with help of library called modelmapper. In next commit

 */

@RestController
@RequestMapping("/employees") //this will work as main url inside it empId comes
public class EmployeeController {

    /*

    */
    private final EmployeeService employeeServiceObj;

    //constructor --> injecting point
    public EmployeeController(EmployeeService employeeServiceObj) {
        this.employeeServiceObj = employeeServiceObj;
    }

    @GetMapping(path="/{empId}")
    public EmployeeEntity sendDTO(@PathVariable(name = "empId") Long empID)
    {
       return employeeServiceObj.getEmployeeById(empID);
    }

    @GetMapping
    public List<EmployeeEntity> allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id , Integer age )
    {
        return employeeServiceObj.getAllEmployee();
    }

    @PostMapping
    public EmployeeEntity createNewEmp(@RequestBody EmployeeEntity inputemp){

        return employeeServiceObj.addEmployee(inputemp);
    }

    @PutMapping
    public String updateEmpById(){
        return "Hello from the put ";
    }
}
