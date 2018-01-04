package com.boot.dto;

import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import static com.boot.validators.CustomUniqueValidator.Unique;

@Getter
@Setter
public class PersonDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Unique(dao = PersonDao.class, fieldName = "email")
    private String email;

    @NotNull
    private Gender gender;

    private Boolean insured;

    @NotNull
    @PositiveOrZero
    private int childrenNumber;
}