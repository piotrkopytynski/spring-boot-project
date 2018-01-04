package com.boot.controller;

import com.boot.AbstractTest;
import com.boot.dao.PersonDao;
import com.boot.entity.Gender;
import com.boot.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PersonControllerTest extends AbstractTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private PersonController personController;

    @MockBean
    private PersonDao personDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldCreateUserWhenGivenCorrectParameters() throws Exception {
        //given
        final String url = PersonController.PERSON_CONTROLLER_MAPPING
                .concat(PersonController.CREATE);
        final String email = "piotr@gek.pl";
        final String name = "jan";
        final String gender = "M";
        final String insured = "true";
        final String childrenNumber = "1";

        //when
        mockMvc.perform(post(url)
                .contentType(MediaType.TEXT_PLAIN)
                .param("name", name)
                .param("email", email)
                .param("gender", gender)
                .param("insured", insured)
                .param("childrenNumber", childrenNumber))
                .andExpect(status().isCreated());

        //then
        verify(personDao).save(new Person(name, email, Gender.M, true, 1));
    }

    @Test
    public void shouldNotCreateUserWhenGivenBlankEmail() throws Exception {
        //given
        final String url = PersonController.PERSON_CONTROLLER_MAPPING
                .concat(PersonController.CREATE);
        final String email = "";
        final String name = "jan";
        final String gender = "M";
        final String insured = "true";
        final String childrenNumber = "1";

        //when
        mockMvc.perform(post(url)
                .contentType(MediaType.TEXT_PLAIN)
                .param("name", name)
                .param("email", email)
                .param("gender", gender)
                .param("insured", insured)
                .param("childrenNumber", childrenNumber))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$", hasItem("must not be blank")));

        //then
        verify(personDao, never()).save(any(Person.class));
    }
}