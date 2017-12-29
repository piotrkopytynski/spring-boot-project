package com.boot.service;

import com.boot.AbstractTest;
import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import com.boot.service.impl.PersonServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.boot.TestObjectFactory.generatePerson;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

public class PersonServiceTest extends AbstractTest {

    @Mock
    private PersonDao personDao;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void shouldSumFilteredPeopleWhenGenderIsGiven() {
        //given
        final Set<Person> people = new HashSet<>(Arrays.asList(
                generatePerson(Gender.M, 0),
                generatePerson(Gender.M, 3),
                generatePerson(Gender.M, 2),
                generatePerson(Gender.M, 1)
        ));

        //when
        when(personDao.findByGender(Gender.M)).thenReturn(people);
        final int childrenNumber = personService.sumChildrenNumberOfFilteredPeople(Gender.M);

        //then
        assertThat(childrenNumber).isEqualTo(6);
    }

    @Test
    public void shouldReturnZeroWhenPeopleHaveNoChildren() {
        //given
        final Set<Person> people = new HashSet<>(Arrays.asList(
                generatePerson(Gender.M, 0),
                generatePerson(Gender.M, 0),
                generatePerson(Gender.M, 0),
                generatePerson(Gender.M, 0)
        ));

        //when
        when(personDao.findByGender(Gender.M)).thenReturn(people);
        final int childrenNumber = personService.sumChildrenNumberOfFilteredPeople(Gender.M);

        //then
        assertThat(childrenNumber).isEqualTo(0);
    }

    @Test
    public void shouldReturnZeroWhenNoPeopleAreFound () {
        //given
        final Set<Person> people = new HashSet<>();

        //when
        when(personDao.findByGender(Gender.M)).thenReturn(people);
        final int childrenNumber = personService.sumChildrenNumberOfFilteredPeople(Gender.M);

        //then
        assertThat(childrenNumber).isEqualTo(0);
    }
}
//        gradlowe sprawy
//
//dołączyć spring mvc i napisać testy do contollera, który przyjmie odpowiednie parametry
//w przypadku poprawności parametrów utworzy person i zakomunikuje sukces
//w przypadku niepoprawncyh parametrów zakomunikuje błędy
//ZACZĄC od testów!