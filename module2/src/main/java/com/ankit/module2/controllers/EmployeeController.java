package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
/*
 * @RequestMapping is a class-level or method-level annotation
 * used to define a base URL for handling HTTP requests.
 *
 * When applied at the class level, @RequestMapping acts as a
 * common or parent URL for all request mappings inside the controller.
 *
 * In this code:
 * @RequestMapping("/employees")
 *
 * means that every endpoint defined inside this controller
 * will start with "/employees".
 *
 * This helps in:
 * 1. Grouping related APIs
 * 2. Avoiding repetition of the same URL prefix
 * 3. Improving code readability and maintainability
 *
 * Example resolution in this controller:
 *
 * Class-level mapping:
 * /employees
 *
 * Method-level mappings:
 * 1. @GetMapping("/{empId}")
 *    Final URL → /employees/{empId}
 *
 * 2. @GetMapping
 *    Final URL → /employees
 *
 * @RequestMapping does NOT handle requests by itself;
 * it only defines a mapping path.
 * The actual HTTP method handling is done by
 * @GetMapping, @PostMapping, @PutMapping, etc.
 *
 * @RequestMapping can also be used at the method level,
 * but in modern Spring Boot applications,
 * it is mainly used at the class level as a base path.
 *
 * Using @RequestMapping at the class level follows
 * REST API design best practices.
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

}
