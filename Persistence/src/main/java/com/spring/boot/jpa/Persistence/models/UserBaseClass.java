package com.spring.boot.jpa.Persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class UserBaseClass {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
}
