package com.spring.springtraining.controller;

import com.spring.springtraining.abstracts.EmployeeService;
import com.spring.springtraining.entities.Employee;
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
    /*@Autowired  this Anutation for dependcy inejection of fileds */
    private final EmployeeService employeeService;

    //this is dependcy injection by constrocttor
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<ArrayList<Employee>>> getAllEmployees() {
        return ResponseEntity.ok(
                new GlobalResponse<>(employeeService.getAllEmployees())
        );
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> getOneEmployees(
            @PathVariable UUID employeeId) {
        Employee employee = employeeService.getOneEmployees(employeeId);
        return ResponseEntity.ok(new GlobalResponse<>(employee));
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOneEmployee(
            @RequestBody @Valid Employee employee) {

        Employee createdEmployee = employeeService.createOneEmployee(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new GlobalResponse<>(createdEmployee));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOneEmployee(
            @PathVariable UUID employeeId,
            @RequestBody @Valid Employee employee) {

        Employee updatedEmployee = employeeService.updateOneEmployee(employeeId, employee);

        return ResponseEntity.ok(new GlobalResponse<>(updatedEmployee));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Void>> deleteOneEmployee(
            @PathVariable UUID employeeId) {

        employeeService.deleteOneEmployee(employeeId);

        return ResponseEntity.ok(
                GlobalResponse.successMessage("Employee deleted successfully")
        );
    }
}
