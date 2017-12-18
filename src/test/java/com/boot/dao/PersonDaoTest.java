package com.boot.dao;

import com.boot.AbstractTest;
import com.boot.entity.Address;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PersonDaoTest extends AbstractTest {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private AddressDao addressDao;

    @Test
    public void personShouldHaveAddressAfterPersist() {
        //given
        Person person = new Person("Michael Angelo", "michael@gmail.com", Gender.M, true, 2);
        Address address = new Address("Wrocław", "Reja", "24v", "50-225");

        //when
        address = addressDao.save(address);
        person.addAddress(address);
        person = personDao.save(person);

        //then
        assertThat(personDao.getById(person.getId()).getAddresses()).contains(address);
    }

    @Test
    public void personShouldHaveAddressAfterMerge() {
        //given
        Person person = new Person("Dojo", "dojo@gmail.com", Gender.M, true, 2);
        Address address = new Address("Wrocław", "Prusa", "22", "50-225");

        //when
        address = addressDao.save(address);
        person = personDao.save(person);
        person.addAddress(address);
        personDao.update(person);

        //then
        assertThat(personDao.getById(person.getId()).getAddresses()).contains(address);
    }

    @Test
    public void shouldFindPersonByEmail() {
        //given
        final Person person = personDao.save(new Person("Benjamin", "geeeewww@gmail.com", Gender.M, true, 2));

        //then
        assertThat(personDao.getByEmail(person.getEmail())).isEqualTo(person);
    }

    @Test
    public void shouldThrowExceptionWhenEmailUniquenessViolation() {
        //given
        final Person person = new Person("Freddy", "aaaaa@5gmail.com", Gender.M, true, 2);
        final Person person2 = new Person("Nicole", "aaaaa@5gmail.com", Gender.F, true, 1);

        //when
        personDao.save(person);
        personDao.save(person2);

        //then
        assertThatThrownBy(() -> personDao.findAll()).isInstanceOf(JpaSystemException.class);
    }
}