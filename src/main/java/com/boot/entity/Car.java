package com.boot.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "car")
@Getter
@Setter
@EqualsAndHashCode(of = "registrationNumber", callSuper = false)
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
}