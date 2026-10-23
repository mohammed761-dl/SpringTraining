package com.spring.springtraining.repositories;

import com.spring.springtraining.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
