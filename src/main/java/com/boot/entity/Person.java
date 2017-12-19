package com.boot.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Entity(name = "person")
@Getter
@Setter
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
//    @Check(constraints = "children_number >= 0")
    @Column(name = "children_number", nullable = false)
    private long childrenNumber;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Set<Address> addresses = new HashSet<>();

    @Setter(AccessLevel.NONE)
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

//    public void addCar(final Car car) {
//        cars.add(car);
//        car.setPerson(this);
//    }
//
//    public void removeCar(final Car car) {
//        cars.remove(car);
//        car.setPerson(null);
//    }

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