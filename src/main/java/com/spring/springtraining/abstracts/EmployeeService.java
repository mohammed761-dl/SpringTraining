package com.spring.springtraining.abstracts;

import com.spring.springtraining.DTO.EmployeeCreateDTO;
import com.spring.springtraining.DTO.EmployeeUpdateDTO;
import com.spring.springtraining.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {

    Employee createOneEmployee(EmployeeCreateDTO employee);

    Employee getOneEmployees(UUID id);

    ArrayList<Employee> getAllEmployees();

    Employee updateOneEmployee(UUID employeeId, EmployeeUpdateDTO employee);

    void deleteOneEmployee(UUID id);

}
