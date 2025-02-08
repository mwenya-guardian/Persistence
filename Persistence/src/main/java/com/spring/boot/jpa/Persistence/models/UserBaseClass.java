package com.spring.boot.jpa.Persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class UserBaseClass {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
}
