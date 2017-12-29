package com.boot.dao;

import com.boot.AbstractTest;
import com.boot.entity.Address;
import com.boot.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.boot.TestObjectFactory.generateAddress;
import static com.boot.TestObjectFactory.generatePerson;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressDaoTest extends AbstractTest {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void personShouldHaveAddressAfterPersist() {
        //given
        final Person person = generatePerson();
        final Address address = generateAddress();

        //when
        address.addPerson(person);
        addressDao.save(address);

        //then
        assertThat(addressDao.findOne(address.getId()).getPeople()).contains(person);
    }

    @Test
    public void personShouldHaveAddressAfterMerge() {
        //given
        final Person person = generatePerson();
        final Address address = generateAddress();

        //when
        addressDao.save(address);
        address.addPerson(person);
        addressDao.save(address);

        //then
        assertThat(addressDao.findOne(address.getId()).getPeople()).contains(person);
    }
}