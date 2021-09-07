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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuthApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void verifyAllPersons() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))
                .andDo(print());
    }

    @Test
    public void verifyPersonById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/person/1")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.login").exists())
                .andExpect(jsonPath("$.password").exists())

                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.login").value("parsentev"))
                .andExpect(jsonPath("$.password").value("123"))

                .andDo(print());

    }
}