package com.boot.controller;

import com.boot.dao.PersonDao;
import com.boot.dto.PersonDTO;
import com.boot.dto.PersonMapper;
import com.boot.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(value = PersonController.PERSON_CONTROLLER_MAPPING)
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonMapper personMapper;

    public static final String PERSON_CONTROLLER_MAPPING = "/person";

    public static final String CREATE = "/create";

    @RequestMapping(value = PersonController.CREATE, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createPerson(final @Valid PersonDTO personDTO, final BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            log.error("unable to save person");
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }
        final Person person = personMapper.personDTOToPerson(personDTO);
        personDao.save(person);
        log.info("person saved successfully");
        return new ResponseEntity(HttpStatus.CREATED);
    }
}