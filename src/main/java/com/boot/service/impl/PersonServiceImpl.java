package com.boot.service.impl;

import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(final PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public int sumChildrenNumberOfFilteredPeople(final Gender gender) {
        return personDao.findByGender(gender).stream().mapToInt(Person::getChildrenNumber).sum();
    }
}