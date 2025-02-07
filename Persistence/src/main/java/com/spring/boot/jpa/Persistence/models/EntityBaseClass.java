package com.spring.boot.jpa.Persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class EntityBaseClass {
    private String firstname;
    private String lastname;
    private String address;
    private String province;
    private String district;
    private String nationality;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
}
