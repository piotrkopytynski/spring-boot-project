package com.boot.dao;

import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PersonDao extends JpaRepository<Person, Long>, PersonDaoCustom {
    Person findByEmail(final String email);

    Set<Person> findByGender(final Gender gender);
}