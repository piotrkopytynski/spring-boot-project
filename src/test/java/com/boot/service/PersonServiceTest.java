package com.boot.service;

import com.boot.AbstractTest;
import com.boot.dao.impl.PersonDaoImpl;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PersonServiceTest extends AbstractTest {

    @Mock
    private PersonDaoImpl personDao;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void shouldCountFilteredPersonsWhenGivenCorrectArguments() {
        //given
        Set<Person> persons = new HashSet<>(Arrays.asList(
                new Person("Jenny", "1@gmail.com", Gender.M, true, 0),
                new Person("Benny", "2@gmail.com", Gender.M, true, 3),
                new Person("Menny", "3@gmail.com", Gender.M, true, 2),
                new Person("Lenny", "4@gmail.com", Gender.F, true, 1),
                new Person("Srenny", "5@gmail.com", Gender.M, true, 1),
                new Person("Kenny", "6@gmail.com", Gender.F, true, -1)
        ));

        //when
        when(personDao.findFiltered(any(), any(), any())).thenReturn(persons);
        final long personsNumber = personService.countFilteredPersons(Gender.M, 1, true);

        //then
        assertThat(personsNumber).isEqualTo(6);
    }
}