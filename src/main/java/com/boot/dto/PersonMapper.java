package com.boot.dto;

import com.boot.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {

    Person personDTOToPerson(final PersonDTO personDTO);
}