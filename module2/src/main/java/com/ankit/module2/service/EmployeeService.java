/*
* The Service layer return response to Controller and Controller must be deal with DTO not with Entity
* We need to convert the Entity to DTO  by using a library called as Model mapping
* Add the dependencies of the model mapping
*  */

package com.ankit.module2.service;
import com.ankit.module2.Exceptions.ResourceNotFoundExcp;
import com.ankit.module2.dto.EmployeeDTO;
import com.ankit.module2.entities.EmployeeEntity;
import com.ankit.module2.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Optional<EmployeeDTO> getEmployeeById(Long id)
    {
       return employeeRepository.findById(id).map(emp->modelMapperObj.map(emp,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployee() {
        List<EmployeeEntity> empEntityList = employeeRepository.findAll();
        if (empEntityList==null) throw new ResourceNotFoundExcp("No Employee is in data base");
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

    public EmployeeDTO   updateEmpById(EmployeeDTO empDTO, Long id) {
        boolean exits = isExistsByEmployeeId(id);
        if(!exits) throw new ResourceNotFoundExcp("(Exception from the Service layer )UpdateEmpById is fail because Resource is not found "+id);
        EmployeeEntity emp = modelMapperObj.map(empDTO,EmployeeEntity.class);
        emp.setId(id);
        return  modelMapperObj.map(employeeRepository.save(emp),EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id)
    {
            isExistsByEmployeeId(id);
            employeeRepository.deleteById(id);
            return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exit = isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapperObj.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    private boolean isExistsByEmployeeId(Long employeeId) {
        boolean exits= employeeRepository.existsById(employeeId);
        if(!exits) throw new ResourceNotFoundExcp("Unable to Delete because Id is not found");
        return exits;
    }
}
