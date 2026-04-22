package com.ankit.module4.clients;

import com.ankit.module4.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {
    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO addEmployee(EmployeeDTO empRequest);
}
