package ru.job4j.auth.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.auth.AuthApplication;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuthApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        personRepository.save(new Person(1,"pavlovi", "pass"));
        personRepository.save(new Person(2,"arsentevp", "pass"));
        personRepository.save(new Person(3,"ivanovi", "pass"));
    }

    @Test
    public void verifyAllPersons() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }

    @Test
    public void verifyPersonById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.login").exists())
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.login").value("arsentevp"))
                .andExpect(jsonPath("$.password").value("pass"))
                .andDo(print());
    }

}
