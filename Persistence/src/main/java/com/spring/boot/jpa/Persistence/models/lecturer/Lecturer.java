package com.spring.boot.jpa.Persistence.models.lecturer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.boot.jpa.Persistence.models.department.Department;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Lecturer {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Department department;

    @OneToOne(mappedBy = "HOD")
    @JsonManagedReference
    private Department department_head;

}
