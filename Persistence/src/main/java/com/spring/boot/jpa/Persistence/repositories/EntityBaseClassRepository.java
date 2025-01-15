package com.spring.boot.jpa.Persistence.repositories;

import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface EntityBaseClassRepository <T> {
    T findByPhoneNumber(String number);
    List<T> findAllByFirstnameContaining(String firstName);
    List<T> findAllByLastnameContaining(String lastName);
    List<T> findAllByProvince(String province);
    List<T> findAllByDistrict(String district);
    List<T> findAllByNationality(String country);
    List<T> findAllByDobAfter(Date date);
    List<T> findAllByDobBefore(Date date);
    List<T> findAllByDobBetween(Date startDate, Date endDate);
}
