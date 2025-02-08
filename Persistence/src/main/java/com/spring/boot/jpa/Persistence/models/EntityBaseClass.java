package com.spring.boot.jpa.Persistence.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@MappedSuperclass
//@SuperBuilder
public class EntityBaseClass extends UserBaseClass{
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
