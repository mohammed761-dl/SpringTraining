package com.spring.springtraining.abstracts;

import com.spring.springtraining.entities.Employee;

import java.util.ArrayList;
import java.util.UUID;

public interface EmployeeService {

    Employee createOneEmployee(Employee employee);

    Employee getOneEmployees(UUID id);

    ArrayList<Employee> getAllEmployees();

    Employee updateOneEmployee(UUID employeeId, Employee employee);

    void deleteOneEmployee(UUID id);

}
