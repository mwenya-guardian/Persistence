package com.spring.boot.jpa.Persistence.models.lecturer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Lecturer {
    @Id
    private Integer id;
}
