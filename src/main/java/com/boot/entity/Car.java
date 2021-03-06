package com.boot.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "car")
@RequiredArgsConstructor
@EqualsAndHashCode(of = "registrationNumber", callSuper = false)
@Getter
@Setter
public class Car extends AbstractEntity {

    @NotBlank
    @Length(max = 255)
    @lombok.NonNull
    @Column(name = "registration_number", nullable = false, unique = true, length = 255)
    private String registrationNumber;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "person_id")
    private Person person;
}