package com.boot;

import com.boot.entity.Address;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class TestObjectFactory
{
    public static Address generateAddress() {
        return new Address(
                RandomStringUtils.randomAlphanumeric(8),
                RandomStringUtils.randomAlphanumeric(8),
                RandomStringUtils.randomAlphanumeric(8),
                RandomStringUtils.randomAlphanumeric(8)
        );
    }

    public static Person generatePerson() {
        return new Person(
                RandomStringUtils.randomAlphanumeric(8),
                RandomStringUtils.randomAlphanumeric(8),
                Gender.F,
                RandomUtils.nextBoolean(),
                RandomUtils.nextInt()
        );
    }

    public static Person generatePerson(final Gender gender) {
        final Person person = generatePerson();
        person.setGender(gender);
        return person;
    }

    public static Person generatePerson(final Gender gender, final int childrenNumber) {
        final Person person = generatePerson();
        person.setGender(gender);
        person.setChildrenNumber(childrenNumber);
        return person;
    }

    public static Person generatePerson(final Gender gender, final boolean insured, final int childrenNumber) {
        final Person person = generatePerson();
        person.setGender(gender);
        person.setInsured(insured);
        person.setChildrenNumber(childrenNumber);
        return person;
    }
}