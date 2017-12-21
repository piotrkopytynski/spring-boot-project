package com.boot.service;

import com.boot.entity.Gender;
import com.boot.entity.Person;

import java.util.Set;

public interface PersonService {

    long countFilteredPersons(final Gender gender, final Integer childrenNumber, final Boolean insured);
}