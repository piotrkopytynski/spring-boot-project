package com.boot.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Entity(name = "address")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"city", "street", "house_number", "postal_code"}))
@Getter
@Setter
@EqualsAndHashCode(of = {"city", "street", "houseNumber", "postalCode"}, callSuper = false)
public class Address extends AbstractEntity {

    @NotBlank
    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @Column(name = "street", nullable = true, length = 255)
    private String street;

    @NotBlank
    @Column(name = "house_number", nullable = false, length = 16)
    private String houseNumber;

    @NotBlank
    @Column(name = "postal_code", nullable = false, length = 16)
    private String postalCode;

    @Setter(AccessLevel.NONE)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "address_person",
            joinColumns = @JoinColumn(
                    name = "address_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "person_id"
            ),
            indexes = {
                    @Index(name = "address_id_index", columnList = "address_id"),
                    @Index(name = "person_id_index", columnList = "person_id"),
            }
    )
    Set<Person> persons = new HashSet<>();

    public Address() {
    }

    public Address(final String city, final String street, final String houseNumber, final String postalCode) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public Set<Person> getPersons() {
        return unmodifiableSet(persons);
    }

    public void addPerson(final Person person) {
        persons.add(person);
        person.addresses.add(this);
    }

    public void removePerson(final Person person) {
        persons.remove(person);
        person.addresses.remove(this);
    }
}