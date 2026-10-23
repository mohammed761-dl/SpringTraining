package com.spring.springtraining.controller;


import com.spring.springtraining.DTO.DepartmentCreateDTO;
import com.spring.springtraining.DTO.DepartmentUpdateDTO;
import com.spring.springtraining.abstracts.DepartmentService;
import com.spring.springtraining.entities.Department;
import com.spring.springtraining.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> createOneDepartment(
            @RequestBody @Valid DepartmentCreateDTO department) {

        Department createddepartment = departmentService.createOneDepartment(department);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GlobalResponse<>(createddepartment));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> getAllDepartments() {
        return ResponseEntity.ok(
                new GlobalResponse<>(departmentService.getAllDepartments())
        );
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> getOneDepartment(
            @PathVariable UUID departmentId) {
        Department department = departmentService.getOneDepartment(departmentId);
        return ResponseEntity.ok(new GlobalResponse<>(department));
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> updateOneDepartment(
            @PathVariable UUID departmentId,
            @RequestBody @Valid DepartmentUpdateDTO department) {

        Department updateddepartment = departmentService.updateOneDepartment(departmentId, department);

        return ResponseEntity.ok(new GlobalResponse<>(updateddepartment));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Void>> deleteOneDepartment(
            @PathVariable UUID departmentId) {

        departmentService.deleteOneDepartment(departmentId);

        return ResponseEntity.ok(
                GlobalResponse.successMessage("Department deleted successfully")
        );
    }
}
