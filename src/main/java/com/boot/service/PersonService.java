package com.boot.service;

import com.boot.entity.Gender;
import com.boot.entity.Person;

import java.util.Set;

public interface PersonService extends AbstractService<Person> {

    long countPersonsByGivenGenderAndMinimalChildrenNumber(final Gender gender, final int minimalChildrenNumber);
}