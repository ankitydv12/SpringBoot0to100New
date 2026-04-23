package com.ankit.module4.clients;

import com.ankit.module4.Exception.ResourceNotFoundException;
import com.ankit.module4.advice.ApiResponse;
import com.ankit.module4.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImp implements EmployeeClient{

    private final RestClient restClient;
    private final EmployeeDTO employeeDTO;
    Logger log = LoggerFactory.getLogger(EmployeeClientImp.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to Retrieve all employees Method in getAllEmployees");
        try {
            log.info("Attempting to call the restclient method");
            ApiResponse<List<EmployeeDTO>> employeeDTOSList =  restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new RuntimeException("could not get the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrived the all employees ");
            log.trace("List of employees from restclient: {}",employeeDTOSList.getData());
            return employeeDTOSList.getData();
        } catch (Exception e) {
            log.error("Exception Occure in getAllEmployee",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO empRequest) {
        log.trace("Enter in the addEmployee Method with request: {}",empRequest);
        try {
            log.info("Going to Add an employeee");
            ApiResponse<EmployeeDTO> employees = restClient.post()
                    .uri("employees")
                    .body(empRequest)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Adding of new emp is done");
            log.trace("Retrieved employees list in getEmployees: {}",employees.getData());
            return employees.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
