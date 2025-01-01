package com.spring.boot.jpa.Persistence.repositories;

import java.util.List;

public interface EntityBaseClassRepository <T> {
    T findByPhoneNumber(String number);
    T findByNrcNumber(String nrc);
    void deleteByNrcNumber(String nrc);
    List<T> findAllByFirstnameContaining(String firstName);
    List<T> findAllByLastnameContaining(String lastName);
    List<T> findAllByProvince(String province);
    List<T> findAllByDistrict(String district);
    List<T> findAllByCountry(String country);
    // List<T> findAllByDobAfter(Date date);


}
