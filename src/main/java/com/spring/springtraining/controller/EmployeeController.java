package com.spring.springtraining.controller;

import com.spring.springtraining.entities.Employee;
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
    public ResponseEntity<ArrayList<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getOneEmployees(@PathVariable UUID employeeId) {

        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElse(null);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createOneEmployee(@RequestBody Employee employee) {

        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());

        employees.add(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateOneEmployee(
            @PathVariable UUID employeeId,
            @RequestBody Employee employee) {

        Employee existingEmployee = employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElse(null);

        if (existingEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setPosition(employee.getPosition());

        return ResponseEntity.ok(existingEmployee);
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
