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
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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

    public EmployeeDTO updateEmpById(EmployeeDTO empDTO, Long id) {
        EmployeeEntity emp = modelMapperObj.map(empDTO,EmployeeEntity.class);
        emp.setId(id);
        return  modelMapperObj.map(employeeRepository.save(emp),EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long id)
    {
        try {
            boolean exits = employeeRepository.existsById(id);
            if(!exits) return false;
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*
     * NOTES: Reflection used in updatePartialEmployeeById method
     *
     * This method uses JAVA REFLECTION to implement PATCH (partial update) behavior.
     * Reflection allows modification of object fields at runtime using field names
     * received from the client.
     */


    /*
     * WHY REFLECTION IS NEEDED HERE
     *
     * - PATCH request sends ONLY the fields to be updated.
     * - Field names are dynamic and not known at compile time.
     * - Writing setter-based logic for every field combination is impractical.
     *
     * Reflection solves this by:
     * - Accessing fields by name (String)
     * - Updating values dynamically at runtime
     */


    /*
     * CORE FLOW OF REFLECTION IN THIS METHOD
     *
     * 1️⃣ Client sends Map<String, Object> updates
     *    - key   → field name (e.g., "name", "age")
     *    - value → new value
     *
     * 2️⃣ Existing Entity is fetched from DB
     *
     * 3️⃣ For each entry in updates Map:
     *    - Find the corresponding Field in EmployeeEntity
     *    - Make it accessible
     *    - Set the new value directly
     */


    /*
     * ReflectionUtils.getRequiredField(EmployeeEntity.class, field)
     *
     * - Searches for a field with the given name in EmployeeEntity.
     * - Throws exception if field does not exist.
     *
     * IMPORTANT:
     * Prevents silent failures when invalid field names are sent.
     */


    /*
     * fieldToBeUpdated.setAccessible(true)
     *
     * - Breaks Java access control checks.
     * - Allows access to private fields.
     *
     * IMPORTANT:
     * This bypasses encapsulation and should be used ONLY in service layer.
     */


    /*
     * ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value)
     *
     * - Sets the new value directly into the entity field.
     * - Happens at runtime.
     * - No setter method is called.
     */


    /*
     * WHAT HAPPENS AFTER REFLECTION
     *
     * - Entity is modified in memory.
     * - employeeRepository.save(entity) is called.
     * - Hibernate detects changed fields (dirty checking).
     * - UPDATE query is generated only for modified columns.
     */

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapperObj.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }

    private boolean isExistsByEmployeeId(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }
}
