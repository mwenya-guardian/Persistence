package com.spring.boot.jpa.Persistence.models.lecture;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Lecture {
    @Id
    private Integer id;
}
