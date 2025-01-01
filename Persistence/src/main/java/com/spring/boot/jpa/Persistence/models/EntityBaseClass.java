package com.spring.boot.jpa.Persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

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
    private String country;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private Date dob;
}
