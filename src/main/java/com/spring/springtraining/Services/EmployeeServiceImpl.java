package com.spring.springtraining.Services;

import com.spring.springtraining.DTO.EmployeeCreateDTO;
import com.spring.springtraining.DTO.EmployeeUpdateDTO;
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
    public Employee createOneEmployee(EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employee.setFirstname(employeeCreateDTO.firstname());
        employee.setLastname(employeeCreateDTO.lastname());
        employee.setEmail(employeeCreateDTO.email());
        employee.setPhoneNumber(employeeCreateDTO.phoneNumber());
        employee.setHireDate(employeeCreateDTO.hireDate());
        employee.setPosition(employeeCreateDTO.position());

        employees.add(employee);
        return employee;
    }


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
    public Employee updateOneEmployee(UUID employeeId, EmployeeUpdateDTO employeeUpdateDTO) {

        Employee existingEmployee = getOneEmployees(employeeId);

        existingEmployee.setFirstname(employeeUpdateDTO.firstname());
        existingEmployee.setLastname(employeeUpdateDTO.lastname());
        existingEmployee.setPosition(employeeUpdateDTO.phoneNumber());
        existingEmployee.setPosition(employeeUpdateDTO.position());

        return existingEmployee;
    }

    @Override
    public void deleteOneEmployee(UUID employeeId) {
        Employee employee = getOneEmployees(employeeId);
        employees.remove(employee);
    }
}
