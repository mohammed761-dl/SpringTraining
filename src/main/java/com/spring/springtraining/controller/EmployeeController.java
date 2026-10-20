package com.spring.springtraining.controller;

import com.spring.springtraining.entities.Employee;
import com.spring.springtraining.shared.CustomResponseException;
import com.spring.springtraining.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> getAllEmployees() {
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> getOneEmployees(@PathVariable UUID employeeId) {

        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()->CustomResponseException.ResourceNotFound(
                        "Employee with id: " + employeeId + " not found"));

        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOneEmployee(@RequestBody @Valid Employee employee) {

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);

        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOneEmployee(
            @PathVariable UUID employeeId,
            @RequestBody @Valid Employee employee) {

        Employee existingEmployee = employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(()->CustomResponseException.ResourceNotFound(
                        "Employee with id: " + employeeId + " not found"));

        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setPosition(employee.getPosition());


        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOneEmployee(@PathVariable UUID employeeId) {

        boolean removed = employees.removeIf(
                employee -> employee.getId().equals(employeeId)
        );

        if (!removed) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
