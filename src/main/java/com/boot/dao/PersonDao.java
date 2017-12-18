package com.boot.dao;

import com.boot.entity.Person;

public interface PersonDao extends AbstractDao<Person> {

    Person getByEmail(final String email);
}