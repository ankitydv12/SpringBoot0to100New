package com.ankit.module2.controllers;


import com.ankit.module2.dto.DepartmentDto;
import com.ankit.module2.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDeparment()
    {
        List<DepartmentDto> AllDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(AllDepartment);
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto inputDep)
    {
        return new ResponseEntity<>(departmentService.addDep(inputDep), HttpStatus.CREATED);
    }

    @PutMapping("/{empId}")
    public ResponseEntity<DepartmentDto> updateDep(@RequestBody DepartmentDto depDto ,@PathVariable(name = "empId") Long id)
    {
        DepartmentDto departmentDto = departmentService.updateById(depDto,id);
        return new ResponseEntity<>(departmentDto,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{empid}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(name = "empid") Long id)
    {
        boolean b = departmentService.delById(id);
        if(b) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{empid}")
    public ResponseEntity<DepartmentDto> getById(@PathVariable(name = "empid") Long id)
    {
       Optional<DepartmentDto> departmentDto=  departmentService.getByid(id);
       return departmentDto.map(departmentDto1 -> ResponseEntity.ok(departmentDto1)).orElse(null);
    }
}
