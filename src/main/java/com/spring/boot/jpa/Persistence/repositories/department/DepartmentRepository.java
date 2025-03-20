package com.spring.boot.jpa.Persistence.repositories.department;

import com.spring.boot.jpa.Persistence.models.department.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    List<Department> findAllByDepartmentNameContaining(String Name);
    Optional<Department> findByDepartmentCode(String code);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Department d WHERE d.departmentCode = :code")
    Integer deleteByDepartmentCodeQuery(String code);

    @Query(value = "SELECT d FROM Department d WHERE d.departmentCode = :code")
    Optional<Department> findByDepartmentCodeQuery(String code);

}
