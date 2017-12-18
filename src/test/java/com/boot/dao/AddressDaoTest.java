package com.boot.dao;

import com.boot.AbstractTest;
import com.boot.entity.Address;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressDaoTest extends AbstractTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void personShouldHaveAddressAfterPersist() {
        //given
        final Person person = new Person("frgeghdy", "f1r4y5ge5@5gmgil.com", Gender.M, true, 2);
        final Address address = new Address("geij", "ejile", "63g", "2523");

        //when
        address.addPerson(person);
        addressDao.save(address);

        //then
        assertThat(addressDao.getById(address.getId()).getPersons()).contains(person);
    }

    @Test
    public void personShouldHaveAddressAfterMerge() {
        //given
        final Person person = new Person("frgeegghdy", "f1r4y5ge5eg@5gmgil.com", Gender.M, true, 2);
        final Address address = new Address("geij", "ejile", "63g", "25g23");

        //when
        addressDao.save(address);
        address.addPerson(person);
        addressDao.update(address);

        //then
        assertThat(addressDao.getById(address.getId()).getPersons()).contains(person);
    }
}