package com.boot.service.impl;

import com.boot.dao.AbstractDao;
import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends AbstractServiceImpl<Person> implements PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonServiceImpl(final PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public long countPersonsByGivenGenderAndMinimalChildrenNumber(final Gender gender, final int minimalChildrenNumber) {
        return personDao.findAll().stream()
                .filter(person -> person.getGender().equals(gender))
                .filter(person -> person.getChildrenNumber() >= minimalChildrenNumber)
                .count();
    }

    @Override
    protected AbstractDao<Person> getDao() {
        return personDao;
    }
}