package com.boot.dao;

import com.boot.entity.Gender;
import com.boot.entity.Person;

import java.util.Set;

public interface PersonDao extends AbstractDao<Person> {

    Person findByEmail(final String email);

    Set<Person> findByGender(final Gender gender);

    Set<Person> findFiltered(final Gender gender, final Integer childrenNumber, final Boolean insured);
}