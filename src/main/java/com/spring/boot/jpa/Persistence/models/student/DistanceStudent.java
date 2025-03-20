package com.spring.boot.jpa.Persistence.models.student;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class DistanceStudent extends Student {
    private Date timeOfStudy;
}
