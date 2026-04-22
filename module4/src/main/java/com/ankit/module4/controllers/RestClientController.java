package com.ankit.module4.controllers;

import com.ankit.module4.clients.EmployeeClientImp;
import com.ankit.module4.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/RestClient")
public class RestClientController {

    private final EmployeeClientImp employeeClientImp;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees()
    {
        List<EmployeeDTO> employeeDTOList = employeeClientImp.getAllEmployees();
        return ResponseEntity.ok(employeeDTOList);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO request)
    {
//        EmployeeDTO request = EmployeeDTO.builder()
//                .name("Ankit Yadav")
//                .email("Ankit@gmail.com")
//                .role("Dev")
//                .salary(898783.32)
//                .dateOfJoining(LocalDate.of(2026, 4, 22))
//                .isActive(true)
//                .prime(3)
//                .build();
        EmployeeDTO employeeDTO = employeeClientImp.addEmployee(request);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }
}
