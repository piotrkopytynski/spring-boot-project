package com.boot.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Entity(name = "person")
@NoArgsConstructor
@EqualsAndHashCode(of = "email", callSuper = false)
@Getter
@Setter
public class Person extends AbstractEntity {

    @NotBlank
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true, length = 254)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 1)
    private Gender gender;

    @Column(name = "insured", nullable = true)
    private Boolean insured;

    @NotNull
    @PositiveOrZero
    @Column(name = "children_number", nullable = false)
    private int childrenNumber;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "persons", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    Set<Address> addresses = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "person", cascade = {CascadeType.MERGE})
    private Set<Car> cars = new HashSet<>();

    public Person(final String name, final String email, final Gender gender, final boolean insured, final int childrenNumber) {
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
}