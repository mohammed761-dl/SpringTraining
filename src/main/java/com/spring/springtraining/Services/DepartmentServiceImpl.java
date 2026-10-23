package com.spring.springtraining.Services;

import com.spring.springtraining.DTO.DepartmentCreateDTO;
import com.spring.springtraining.DTO.DepartmentUpdateDTO;
import com.spring.springtraining.abstracts.DepartmentService;
import com.spring.springtraining.entities.Department;
import com.spring.springtraining.repositories.DepartmentRepo;
import com.spring.springtraining.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department createOneDepartment(DepartmentCreateDTO dto) {

        if (departmentRepo.existsByNameIgnoreCase(dto.name())) {
            throw CustomResponseException.Conflict(
                    "Department already exists"
            );
        }

        Department department = new Department();
        department.setName(dto.name());

        return departmentRepo.save(department);
    }

    @Override
    public Department getOneDepartment(UUID departmentid) {
        return departmentRepo.findById(departmentid)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Department with id: " + departmentid + " not found"
                ));
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    @Override
    public Department updateOneDepartment(UUID departmentId, DepartmentUpdateDTO department) {
        return null;
    }

    @Override
    public void deleteOneDepartment(UUID id) {

    }
}
