package com.spring.springtraining.abstracts;

import com.spring.springtraining.DTO.DepartmentCreateDTO;
import com.spring.springtraining.DTO.DepartmentUpdateDTO;
import com.spring.springtraining.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department createOneDepartment(DepartmentCreateDTO department);

    Department getOneDepartment(UUID departmentId);

    List<Department> getAllDepartments();

    Department updateOneDepartment(UUID departmentId, DepartmentUpdateDTO department);

    void deleteOneDepartment(UUID id);
}
