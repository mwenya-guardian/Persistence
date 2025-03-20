package com.spring.boot.jpa.Persistence.repositories;

import com.spring.boot.jpa.Persistence.models.UserBaseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserBaseClass, Integer> {
    @Query(value = "SELECT u FROM UserBaseClass u WHERE u.username = :username")
    Optional<UserBaseClass> findByUsername(@Param("username") String username);
}
