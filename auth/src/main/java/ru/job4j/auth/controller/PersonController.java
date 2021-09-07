package ru.job4j.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.domain.Response;
import ru.job4j.auth.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(
                personService.findAllPersons(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        Optional<Person> person = this.personService.findPersonById(id);
        return new ResponseEntity<Person>(
                person.orElse(new Person()),
                person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return new ResponseEntity<Person>(
                this.personService.savePerson(person),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        return new ResponseEntity<Person>(
                this.personService.savePerson(person),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        this.personService.deletePerson(person);
        return new ResponseEntity<Response>(
                new Response(HttpStatus.OK.value(), "Person has been deleted"),
                HttpStatus.OK);
    }
}
