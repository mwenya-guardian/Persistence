package com.spring.boot.jpa.Persistence.repositories;

import com.spring.boot.jpa.Persistence.models.UserBaseClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBaseClass, Integer> {
    Optional<UserBaseClass> findByUsername(String username);
}
