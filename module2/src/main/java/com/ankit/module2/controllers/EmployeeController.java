package com.ankit.module2.controllers;
import com.ankit.module2.dto.EmployeeDTO;
import com.ankit.module2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/employees") //this will work as main url inside it empId comes
public class EmployeeController {
    private final EmployeeService employeeServiceObj;

    //constructor --> injecting point
    public EmployeeController(EmployeeService employeeServiceObj) {
        this.employeeServiceObj = employeeServiceObj;
    }

    @GetMapping(path="/{empId}")
    public ResponseEntity<EmployeeDTO> sendDTO(@PathVariable(name = "empId") Long empID)
    {
       Optional<EmployeeDTO> employeeDTO =  employeeServiceObj.getEmployeeById(empID);
       return employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElseThrow(()->new NoSuchElementException());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleResourceNotFound(NoSuchElementException exception)
    {
        return new ResponseEntity<>("No Resouce found",HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> allEmpList(@RequestParam(required = false,name = "inputid" , defaultValue = "123") Integer id , Integer age )
    {
        System.out.println(employeeServiceObj.getAllEmployee());
       List<EmployeeDTO> allEmpDto = employeeServiceObj.getAllEmployee();
       return ResponseEntity.ok(allEmpDto);

    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO inputemp){
        EmployeeDTO newDTO  =  employeeServiceObj.addEmployee(inputemp);
        return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
    }

    //http://localhost:8080/employees/2
    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmpById(@RequestBody EmployeeDTO empDTO,@PathVariable(name = "employeeId")Long Id){
        EmployeeDTO updateDto=   employeeServiceObj.updateEmpById(empDTO,Id);
        return ResponseEntity.ok(updateDto);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "employeeId") Long id)
    {
        boolean gotDeleted = employeeServiceObj.deleteEmployeeById(id);
        if(gotDeleted) return ResponseEntity.ok(gotDeleted);
        return ResponseEntity.notFound().build();
    }
    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                                 @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeServiceObj.updatePartialEmployeeById(employeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
