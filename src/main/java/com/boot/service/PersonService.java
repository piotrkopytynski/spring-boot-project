package com.boot.service;

import com.boot.entity.Gender;

public interface PersonService {

    int sumChildrenNumberOfFilteredPeople(final Gender gender);
}