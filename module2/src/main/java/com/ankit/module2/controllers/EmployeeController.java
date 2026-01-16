package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*
    methods allEmpList() and createNewEmp() are come under the parent url (/employees)
    By Default browser send the get request so allEmpList execute
    We can send Post request from the front-end or using Postman

 */

@RestController
@RequestMapping("/employees") //this will work as main url inside it empId comes
public class EmployeeController {

    //http://localhost:8080/employees/121
    @GetMapping(path="/{empId}")
    public EmployeDTO sendDTO(@PathVariable(name = "empId") Long empID)
    {
        return new EmployeDTO(empID,"Ankit yadav",21,4500000,"Kanpur Nagar","DevOps");
    }

    //http://localhost:8080/employees?inputid=7&age=21
    @GetMapping
    public String allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id ,   Integer age )
    {
    return "Hello " + id + " " + "My age is "+ age;
    }

    @PostMapping
    public String createNewEmp(){
        return "I am new Employee";
    }

    @PutMapping
    public String updateEmpById(){
        return "Hello from the put ";
    }
}
