package com.ankit.module2.service;

import com.ankit.module2.Exceptions.ResourceNotFoundExcp;
import com.ankit.module2.dto.DepartmentDto;
import com.ankit.module2.entities.Department;
import com.ankit.module2.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository repositoryObj;
    private  final ModelMapper modelMapperObj;

    public DepartmentService(DepartmentRepository repositoryObj, ModelMapper modelMapperObj) {
        this.repositoryObj = repositoryObj;
        this.modelMapperObj = modelMapperObj;
    }

    public List<DepartmentDto> getAllDepartment() {
        System.out.println("Debug : DepartmentServiceLayer getAllDepartment");
        List<Department> depEntities = repositoryObj.findAll();
        if (depEntities.isEmpty()) throw new ResourceNotFoundExcp("The Department Table is Empty");
        return depEntities.stream()
                .map(department -> modelMapperObj.map(department,DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public DepartmentDto addDep(DepartmentDto inpDep) {
        Department newDep = modelMapperObj.map(inpDep,Department.class);
        repositoryObj.save(newDep);
        return modelMapperObj.map(newDep,DepartmentDto.class);
    }

    public DepartmentDto updateById(DepartmentDto inpDto,Long id) {
        exitDep(id);
        Department entity = modelMapperObj.map(inpDto,Department.class);
        entity.setId(id);
        return modelMapperObj.map(repositoryObj.save(entity),DepartmentDto.class);
    }



    public void exitDep(Long id)
    {
        repositoryObj.findById(id).orElseThrow(()->new ResourceNotFoundExcp("No dep with this id Exits"));
    }

    public boolean delById(Long id) {
        exitDep(id);
        repositoryObj.deleteById(id);
        return true;
    }

    public Optional<DepartmentDto> getByid(Long id) {
        exitDep(id);
        Optional<Department> dep = repositoryObj.findById(id);
        return dep.map(department -> modelMapperObj.map(department,DepartmentDto.class));
    }
}
