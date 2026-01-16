package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*
 */

@RestController
@RequestMapping("/employees") //this will work as main url inside it empId comes
public class EmployeeController {
    /*
    RequestBody is used to send the complex data like json and xml from the client or Postman through POST
    */
    @PostMapping
    public EmployeeDTO createNewEmp(@RequestBody EmployeeDTO inputemp) {

        inputemp.setId(100L);
        return inputemp;
    }
}
