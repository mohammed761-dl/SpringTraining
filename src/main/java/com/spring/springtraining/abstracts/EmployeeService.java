package com.spring.springtraining.abstracts;

import com.spring.springtraining.entities.Employee;

import java.util.*;

public interface EmployeeService {
    Employee getOneEmployees(UUID id);
    ArrayList<Employee> getAllEmployees();
    void deleteOneEmployee(UUID id);
    Employee createOneEmployee(Employee employee);
    Employee updateOneEmployee(UUID employeeId, Employee employee);
}
