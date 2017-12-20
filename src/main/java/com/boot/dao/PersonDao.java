package com.boot.dao;

import com.boot.entity.Gender;
import com.boot.entity.Person;

import java.util.Set;

public interface PersonDao extends AbstractDao<Person> {

    Person findByEmail(final String email);

    Set<Person> findByGender(final Gender gender);

    Set<Person> findFiltered(Gender gender, Integer childrenNumber, Boolean insured);
}