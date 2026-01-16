package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    /*
        *This is the example of the @GetMapping("/getSecretMessage")
    */
    @GetMapping("/getSecretMessage")
    public String  getMySuperSecretMessage()
    {
        return "Sceret message: jadhjadj";
    }

    /*
     * Sending an object from the server to the client.
     * This object is called a DTO (Data Transfer Object).
     *
     * In @GetMapping, the path can be an absolute path, which means
     * the client must provide the empId as part of the URL.
     *
     * The path variable defined in @GetMapping must be mapped
     * to a method parameter using @PathVariable.
     *
     * @PathVariable(name = "empId") binds the value of "empId"
     * from the URL to the method parameter.
     *
     * If @PathVariable(name = "empId") is used, the method parameter
     * name can be different (for example, Long id).
     *
     * If the name attribute is not specified in @PathVariable,
     * then the variable name in the URL (e.g., {empId})
     * must exactly match the method parameter name.
     *
     * Example:
     * @GetMapping("/employees/{empId}")
     * public EmployeeDTO getEmployee(@PathVariable Long empId) { ... }
     */

    @GetMapping(path="/{empId}")
    public EmployeDTO sendDTO(@PathVariable(name = "empId") Long empID)
    {
        return new EmployeDTO(empID,"Ankit yadav",21,4500000,"Kanpur Nagar","DevOps");
    }

    /*
     * @RequestParam is used to extract query parameters from the client request URL.
     * These parameters are sent after the '?' symbol in the URL.

     * The parameter name in the URL must match the method parameter name,
     * unless the name attribute is explicitly specified.

     * By default, @RequestParam is mandatory.
     * If the client does not provide the parameter, Spring throws an error.

     * To make a parameter optional, use required = false.
     *
     * Example:
     * @RequestParam(required = false) String department

     * Default values can be assigned using defaultValue.
     * If the client does not send the parameter, the default value is used.

     * Example:
     * @RequestParam(defaultValue = "0") int page
     *
     * If @RequestParam(name = "inputage") is used, the method parameter
     * name can be different from the query parameter name(i.e url value).
    * */

    //http://localhost:8080/employees?inputid=7&age=21
    @GetMapping("/employees")
    public String allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id ,   Integer age )
    {
    return "Hello " + id + " " + "My age is "+ age;
    }

}
