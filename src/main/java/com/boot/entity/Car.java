package com.boot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "car")
public class Car extends AbstractEntity {

    @NotBlank
    @Column(name = "registration_number", nullable = false, unique = true, length = 255)
    private String registrationNumber;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "person_id")
    private Person person;

    public Car() {
    }

    public Car(final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(final String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return registrationNumber.equals(car.registrationNumber);

    }

    @Override
    public int hashCode() {
        return registrationNumber.hashCode();
    }
}