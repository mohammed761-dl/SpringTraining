package com.spring.springtraining.Services;

import com.spring.springtraining.DTO.EmployeeCreateDTO;
import com.spring.springtraining.DTO.EmployeeUpdateDTO;
import com.spring.springtraining.abstracts.EmployeeService;
import com.spring.springtraining.entities.Employee;
import com.spring.springtraining.repositories.EmployeeRepo;
import com.spring.springtraining.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public Employee createOneEmployee(EmployeeCreateDTO employeeCreateDTO) {
        Employee employee = new Employee();


        employee.setFirstname(employeeCreateDTO.firstname());
        employee.setLastname(employeeCreateDTO.lastname());
        employee.setEmail(employeeCreateDTO.email());
        employee.setPhoneNumber(employeeCreateDTO.phoneNumber());
        employee.setHireDate(employeeCreateDTO.hireDate());
        employee.setPosition(employeeCreateDTO.position());

        employeeRepo.save(employee);
        return employee;
    }


    @Override
    public Employee getOneEmployees(UUID employeeId) {
        return employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id: " + employeeId + " not found"
                ));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee updateOneEmployee(UUID employeeId, EmployeeUpdateDTO employeeUpdateDTO) {

        Employee existingEmployee = getOneEmployees(employeeId);

        existingEmployee.setFirstname(employeeUpdateDTO.firstname());
        existingEmployee.setLastname(employeeUpdateDTO.lastname());
        existingEmployee.setPhoneNumber(employeeUpdateDTO.phoneNumber());
        existingEmployee.setPosition(employeeUpdateDTO.position());

        return employeeRepo.save(existingEmployee);
    }

    @Override
    public void deleteOneEmployee(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with id: " + employeeId + " not found"
                ));

        employeeRepo.delete(employee);
    }

}
