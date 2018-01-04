package com.boot.dao;

import com.boot.entity.Gender;
import com.boot.entity.Person;

import java.util.Set;

public interface PersonDaoCustom extends AbstractDao {
    Set<Person> findFiltered(final Gender genderParam, final Integer childrenNumber,final Boolean insured);
}