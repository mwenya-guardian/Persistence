package com.spring.boot.jpa.Persistence.repositories.department;

import com.spring.boot.jpa.Persistence.models.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public void deleteByDepartmentCode(String code);
    public Department findByDepartmentCode(String code);
    public List<Department> findAllByDepartmentNameContaining(String Name);
}
