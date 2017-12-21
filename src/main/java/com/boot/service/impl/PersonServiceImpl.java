package com.boot.service.impl;

import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
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
    public long countFilteredPersons(final Gender gender, final Integer childrenNumber, final Boolean insured) {
        return personDao.findFiltered(gender, childrenNumber, insured).stream().count();
    }
}