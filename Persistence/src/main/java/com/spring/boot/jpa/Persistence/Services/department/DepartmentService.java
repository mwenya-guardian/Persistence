package com.spring.boot.jpa.Persistence.Services.department;

import com.spring.boot.jpa.Persistence.repositories.department.DepartmentRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
@NoArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
}
