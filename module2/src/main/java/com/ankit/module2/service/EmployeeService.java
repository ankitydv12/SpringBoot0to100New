/*
* The Service layer return response to Controller and Controller must be deal with DTO not with Entity
* We need to convert the Entity to DTO  by using a library called as Model mapping
* Add the dependencies of the model mapping
*  */

package com.ankit.module2.service;
import com.ankit.module2.dto.EmployeeDTO;
import com.ankit.module2.entities.EmployeeEntity;
import com.ankit.module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service // this creat a bean also  because it contain the component Annotation
public class EmployeeService {
    //beans
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapperObj;

    //constructor or injecting point
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapperObj) {
        this.employeeRepository = employeeRepository;
        this.modelMapperObj = modelMapperObj;
    }

    public EmployeeDTO getEmployeeById(Long id)
    {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);

      return modelMapperObj.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> empEntityList = employeeRepository.findAll();
        return empEntityList
                .stream()
                .map(employeeEntity -> modelMapperObj.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO inputemp) {
        EmployeeEntity inputempEntity =  modelMapperObj.map(inputemp,EmployeeEntity.class);
        employeeRepository.save(inputempEntity);
        return modelMapperObj.map(inputempEntity, EmployeeDTO.class);
    }
}
