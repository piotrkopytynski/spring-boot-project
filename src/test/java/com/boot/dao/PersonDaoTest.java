package com.boot.dao;

import com.boot.AbstractTest;
import com.boot.entity.Address;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.HashSet;
import java.util.Set;

import static com.boot.TestObjectFactory.generateAddress;
import static com.boot.TestObjectFactory.generatePerson;
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
        Person person = generatePerson();
        Address address = generateAddress();

        //when
        address = addressDao.save(address);
        person.addAddress(address);
        person = personDao.save(person);

        //then
        assertThat(personDao.findOne(person.getId()).getAddresses()).contains(address);
    }

    @Test
    public void personShouldHaveAddressAfterMerge() {
        //given
        Person person = generatePerson();
        Address address = generateAddress();

        //when
        address = addressDao.save(address);
        person = personDao.save(person);
        person.addAddress(address);
        personDao.save(person);

        //then
        assertThat(personDao.findOne(person.getId()).getAddresses()).contains(address);
    }

    @Test
    public void shouldFindPersonByEmail() {
        //given
        final Person person = personDao.save(generatePerson());

        //when
        final Person personFoundByEmail = personDao.findByEmail(person.getEmail());

        //then
        assertThat(personFoundByEmail).isEqualTo(person);
    }

    @Test
    public void shouldThrowExceptionWhenEmailIsNull() {
        //given
        final Person person = generatePerson();
        person.setEmail(null);

        //when
        final ThrowableAssert.ThrowingCallable invocation = () -> personDao.save(person);

        //then
        assertThatThrownBy(invocation).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void shouldThrowExceptionWhenEmailUniquenessIsViolated() {
        //given
        final Person person = personDao.save(generatePerson());
        final Person person2 = generatePerson();
        person2.setEmail(person.getEmail());

        //when
        final ThrowableAssert.ThrowingCallable invocation = () -> personDao.save(person2);

        //then
        assertThatThrownBy(invocation).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void shouldReturnNullWhenNoPersonWithGivenEmailFound() {
        //given
        final String notExistingEmail = "random@gmail.com";

        //when
        final Person personFoundByEmail = personDao.findByEmail(notExistingEmail);

        //then
        assertThat(personFoundByEmail).isNull();
    }

    @Test
    public void shouldFindPeopleByGender() {
        //given
        final Person person1 = personDao.save(generatePerson(Gender.M));
        final Person person2 = personDao.save(generatePerson(Gender.M));
        final Person person3 = personDao.save(generatePerson(Gender.M));
        final Person person4 = personDao.save(generatePerson(Gender.F));

        //when
        final Set<Person> peopleByGender = personDao.findByGender(Gender.M);

        //then
        assertThat(peopleByGender).hasSize(3).containsExactlyInAnyOrder(person1, person2, person3);
    }

    @Test
    public void shouldReturnEmptySetWhenNoResultsForGivenGenderFound() {
        //given
        final Set<Person> peopleSet = new HashSet<>();

        //when
        final Set<Person> peopleByGender = personDao.findByGender(Gender.F);

        //then
        assertThat(peopleByGender).isEmpty();
    }

    @Test
    public void shouldReturnEmptySetWhenParamIsNull() {
        //given
        personDao.save(generatePerson(Gender.M));
        personDao.save(generatePerson(Gender.F));

        //when
        final Set<Person> peopleByGender = personDao.findByGender(null);

        //then
        assertThat(peopleByGender).isEmpty();
    }

    @Test
    public void shouldFindFilteredPeople() {
        //given
        initPeople();
        final SoftAssertions softAssertions = new SoftAssertions();

        //then
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 0, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 0, false)).hasSize(0);
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 2, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.M, 2, false)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 0, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 0, false)).hasSize(0);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 3, true)).hasSize(1);
        softAssertions.assertThat(personDao.findFiltered(Gender.F, 3, false)).hasSize(0);

        softAssertions.assertAll();
    }

    @Test
    public void shouldFindFilteredPeopleWhenGenderIsNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(null, 1, true);

        //then
        assertThat(filteredPeople).hasSize(1);
    }

    @Test
    public void shouldFindFilteredPeopleWhenChildrenNumberIsNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(Gender.M, null, true);

        //then
        assertThat(filteredPeople).hasSize(4);
    }

    @Test
    public void shouldFindFilteredPeopleWhenInsuranceIsNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(Gender.M, 2, null);

        //then
        assertThat(filteredPeople).hasSize(2);
    }

    @Test
    public void shouldFindFilteredPeopleWhenChildrenNumberAndGenderAreNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(null, null, true);

        //then
        assertThat(filteredPeople).hasSize(6);
    }

    @Test
    public void shouldFindFilteredPeopleWhenChildrenNumberAndInsuranceAreNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(Gender.M, null, null);

        //then
        assertThat(filteredPeople).hasSize(7);
    }

    @Test
    public void shouldFindFilteredPeopleWhenGenderAndInsuranceAreNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(null, 1, null);

        //then
        assertThat(filteredPeople).hasSize(3);
    }

    @Test
    public void shouldFindFilteredPeopleWhenAllArgsAreNull() {
        //given
        initPeople();

        //when
        final Set<Person> filteredPeople = personDao.findFiltered(null, null, null);

        //then
        assertThat(filteredPeople).hasSize(10);
    }

    private void initPeople() {
        personDao.save(generatePerson(Gender.M, true, 2));
        personDao.save(generatePerson(Gender.M, false, 3));
        personDao.save(generatePerson(Gender.M, false, 2));
        personDao.save(generatePerson(Gender.F, false, 1));
        personDao.save(generatePerson(Gender.M, true, 0));
        personDao.save(generatePerson(Gender.F, true, 0));
        personDao.save(generatePerson(Gender.M, true, 1));
        personDao.save(generatePerson(Gender.F, true, 3));
        personDao.save(generatePerson(Gender.M, false, 1));
        personDao.save(generatePerson(Gender.M, true, 3));
    }
}