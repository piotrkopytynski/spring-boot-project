package com.boot.service;

import com.boot.AbstractTest;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PersonServiceTest extends AbstractTest {

    @Autowired
    private PersonServiceImpl personService;

    @Before
    public void setup() {
        final Person person1 = new Person("Jenny", "1@gmail.com", Gender.M, true, 0);
        final Person person2 = new Person("Benny", "2@gmail.com", Gender.M, true, 3);
        final Person person3 = new Person("Menny", "3@gmail.com", Gender.M, true, 2);
        final Person person4 = new Person("Lenny", "4@gmail.com", Gender.F, true, 1);
        final Person person5 = new Person("Srenny", "5@gmail.com", Gender.M, true, 1);
        final Person person6 = new Person("Kenny", "6@gmail.com", Gender.F, true, -1);

        personService.save(person1);
        personService.save(person2);
        personService.save(person3);
        personService.save(person4);
        personService.save(person5);
        personService.save(person6);
    }

    @Test
    public void shouldReturnProperNumberOfPersonsWhenCorrectArgumentsGiven() {
        //then
        assertThat(personService.countPersonsByGivenGenderAndMinimalChildrenNumber(Gender.M, 1)).isEqualTo(3);
    }

    @Test
    public void shouldReturnEmptySetWhenWrongArgumentsGiven() {
        //then
        assertThat(personService.countPersonsByGivenGenderAndMinimalChildrenNumber(null, 1)).isEqualTo(0);
    }
}