package com.spring.boot.jpa.Persistence.models.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentNumberTable {
    @Id
    private Integer id;
    @Column(length = 4)
    private String year;
    @Column(precision = 6)
    private Long number;
}
