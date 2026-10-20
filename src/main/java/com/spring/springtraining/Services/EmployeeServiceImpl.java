package com.spring.springtraining.Services;

import com.spring.springtraining.abstracts.EmployeeService;
import com.spring.springtraining.entities.Employee;
import com.spring.springtraining.shared.CustomResponseException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public Employee getOneEmployees(UUID employeeId) {
        return employees.stream()
                .filter(e -> e.getId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id: " + employeeId + " not found"));
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public void deleteOneEmployee(UUID employeeId) {
        Employee employee = getOneEmployees(employeeId);
        employees.remove(employee);
    }


    @Override
    public Employee createOneEmployee(Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee updateOneEmployee(UUID employeeId, Employee employee) {

        Employee existingEmployee = getOneEmployees(employeeId);

        existingEmployee.setFirstname(employee.getFirstname());
        existingEmployee.setLastname(employee.getLastname());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhoneNumber(employee.getPhoneNumber());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setPosition(employee.getPosition());

        return existingEmployee;
    }

}
