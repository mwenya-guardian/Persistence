package com.spring.boot.jpa.Persistence.repositories.department;

import com.spring.boot.jpa.Persistence.models.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
