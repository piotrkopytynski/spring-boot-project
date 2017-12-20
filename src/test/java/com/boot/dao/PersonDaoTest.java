package com.boot.dao;

import com.boot.AbstractTest;
import com.boot.entity.Address;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.assertj.core.api.SoftAssertions;
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
        assertThat(personDao.findByEmail(person.getEmail())).isEqualTo(person);
    }

    @Test
    public void shouldFindPersonsByGender() {
        //given
        final Person person1 = personDao.save(new Person("Vincent Vega", "vinnnie@gmail.com", Gender.M, true, 2));
        final Person person2 = personDao.save(new Person("Johnny Bravo", "johnny@gmail.com", Gender.M, true, 2));
        final Person person3 = personDao.save(new Person("Lou Vega", "lv@gmail.com", Gender.M, true, 2));
        final Person person4 = personDao.save(new Person("Lisa Moon", "lisemon@gmail.com", Gender.F, true, 1));

        //then
        assertThat(personDao.findByGender(Gender.M)).hasSize(3).containsExactlyInAnyOrder(person1, person2, person3);
    }

    @Test
    public void shouldFindFilteredPersons() {
        //given
        personDao.save(new Person("Vincent Vega", "vinnnie@gmail.com", Gender.M, true, 2));
        personDao.save(new Person("Johnny Bravo", "johnny@gmail.com", Gender.M, false, 3));
        personDao.save(new Person("Lou Vega", "lv@gmail.com", Gender.M, false, 2));
        personDao.save(new Person("Lisa Moon", "lisemon@gmail.com", Gender.F, false, 1));
        personDao.save(new Person("Ridge Forester", "ridge@gmail.com", Gender.M, true, 0));
        personDao.save(new Person("Barbara Kwarc", "kleks@gmail.com", Gender.F, true, 0));
        personDao.save(new Person("Uzumaki Naruto", "naruto@gmail.com", Gender.M, true, 1));
        personDao.save(new Person("Maria Skłodowska-Curie", "maria@gmail.com", Gender.F, true, 3));
        personDao.save(new Person("Lech Wałęsa", "walesa@gmail.com", Gender.M, false, 1));
        personDao.save(new Person("Malcolm X", "malcolm@gmail.com", Gender.M, true, 3));

        final SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(personDao.findFiltered(Gender.M, 0, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 0, false)).hasSize(0);
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 2, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 2, false)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 0, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 0, false)).hasSize(0);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 3, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 3, false)).hasSize(0);

        softAssertions.assertThat(personDao.findFiltered(null, 1, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(null, 2, false)).hasSize(1);

        softAssertions.assertThat(personDao.findFiltered(Gender.M, null, true)).hasSize(4);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, null, false)).hasSize(1);

        softAssertions.assertThat(personDao.findFiltered(Gender.M, 2, null)).hasSize(2);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 0, null)).hasSize(1);

        softAssertions.assertThat(personDao.findFiltered(Gender.M, null, null)).hasSize(7);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, null, null)).hasSize(3);

        softAssertions.assertThat(personDao.findFiltered(null, 1, null)).hasSize(3);
        softAssertions.assertThat(personDao.findFiltered(null, 3, null)).hasSize(3);


        softAssertions.assertThat(personDao.findFiltered(null, null, true)).hasSize(6);
        softAssertions.assertThat(personDao.findFiltered(null, null, false)).hasSize(4);

        softAssertions.assertThat(personDao.findFiltered(null, null, null)).hasSize(10);

        softAssertions.assertAll();
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