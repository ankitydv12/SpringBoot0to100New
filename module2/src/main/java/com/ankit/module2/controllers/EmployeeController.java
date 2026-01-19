package com.ankit.module2.controllers;

import com.ankit.module2.dto.EmployeeDTO;

import com.ankit.module2.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
/*

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
    public EmployeeDTO sendDTO(@PathVariable(name = "empId") Long empID)
    {
       return employeeServiceObj.getEmployeeById(empID);
    }

    @GetMapping
    public List<EmployeeDTO> allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id , Integer age )
    {
        System.out.println(employeeServiceObj.getAllEmployee());
        return employeeServiceObj.getAllEmployee();
    }

    @PostMapping
    public EmployeeDTO createNewEmp(@RequestBody EmployeeDTO inputemp){

        return employeeServiceObj.addEmployee(inputemp);
    }

    //http://localhost:8080/employees/2
    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmpById(@RequestBody EmployeeDTO empDTO,@PathVariable(name = "employeeId")Long Id){
        return  employeeServiceObj.updateEmpById(empDTO,Id);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
        return employeeServiceObj.deleteEmployeeById(id);
    }
}
