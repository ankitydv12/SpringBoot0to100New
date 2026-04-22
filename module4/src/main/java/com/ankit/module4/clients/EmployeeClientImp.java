package com.ankit.module4.clients;

import com.ankit.module4.advice.ApiResponse;
import com.ankit.module4.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImp implements EmployeeClient{

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOSList =  restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOSList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO empRequest) {
        try {
            ApiResponse<EmployeeDTO> employees = restClient.post()
                    .uri("employees")
                    .body(empRequest)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employees.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
