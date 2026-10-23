package com.spring.springtraining.abstracts;

import com.spring.springtraining.DTO.EmployeeCreateDTO;
import com.spring.springtraining.DTO.EmployeeUpdateDTO;
import com.spring.springtraining.entities.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee createOneEmployee(EmployeeCreateDTO employee);

    Employee getOneEmployees(UUID id);

    List<Employee> getAllEmployees();

    Employee updateOneEmployee(UUID employeeId, EmployeeUpdateDTO employee);

    void deleteOneEmployee(UUID id);

}
