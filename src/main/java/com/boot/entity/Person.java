package com.boot.entity;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Entity(name = "person")
public class Person extends AbstractEntity {

    @NotBlank
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Email
    @Column(name = "email", unique = true, length = 254)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 1)
    private Gender gender;

    @Column(name = "insured", nullable = true)
    private Boolean insured;

    @NotNull
    @PositiveOrZero
    @Check(constraints = "children_number >= 0")
    @Column(name = "children_number", nullable = false)
    private long childrenNumber;

    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE})
    private Set<Car> cars = new HashSet<>();

    public Person() {
    }

    public Person(final String name, final String email, final Gender gender, final boolean insured, final long childrenNumber) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.insured = insured;
        this.childrenNumber = childrenNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public boolean getInsured() {
        return insured;
    }

    public void setInsured(final boolean insured) {
        this.insured = insured;
    }

    public long getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(final long childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public Set<Address> getAddresses() {
        return unmodifiableSet(addresses);
    }

    public void addAddress(final Address address) {
        addresses.add(address);
        address.persons.add(this);
    }

    public void removeAddress(final Address address) {
        addresses.remove(address);
        address.persons.remove(this);
    }

    public Set<Car> getCars() {
        return unmodifiableSet(cars);
    }

    public void addCar(final Car car) {
        cars.add(car);
        car.setPerson(this);
    }

    public void removeCar(final Car car) {
        cars.remove(car);
        car.setPerson(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return email != null ? email.equals(person.email) : person.email == null;

    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}