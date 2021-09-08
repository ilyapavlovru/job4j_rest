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
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.EmployeeService;
import ru.job4j.auth.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AuthApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private PersonService personService;

    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Employee employee = employeeService.saveEmployee(Employee.of("ilya", "pavlov", "inn123456"));
        personService.savePerson(new Person(1,"pavlovi", "pass", employee));
        personService.savePerson(new Person(2,"arsentevp", "pass", employee));
        personService.savePerson(new Person(3,"ivanovi", "pass", employee));
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

    @Test
    public void verifySavePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\" : \"job4j@gmail.com\", \"password\" : \"123\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.login").exists())
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.login").value("job4j@gmail.com"))
                .andExpect(jsonPath("$.password").value("123"))
                .andDo(print());
    }

    @Test
    public void verifyUpdatePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"1\", \"login\" : \"pavlovia\", \"password\" : \"newpass\" }")
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.login").exists())
                .andExpect(jsonPath("$.password").exists())

                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.login").value("pavlovia"))
                .andExpect(jsonPath("$.password").value("newpass"))
                .andDo(print());
    }

    @Test
    public void verifyDeletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/person/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Person has been deleted"))
                .andDo(print());
    }
}
