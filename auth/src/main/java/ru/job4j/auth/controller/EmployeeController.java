package ru.job4j.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.service.EmployeeService;
import ru.job4j.auth.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final PersonService personService;

    public EmployeeController(EmployeeService employeeService, PersonService personService) {
        this.employeeService = employeeService;
        this.personService = personService;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAll() {
        return new ResponseEntity<List<Employee>>(
                employeeService.findAllEmployees(),
                HttpStatus.OK);
    }

    @PostMapping("/{id}/person")
    public ResponseEntity<Employee> addPersonToEmployee(@PathVariable int id, @RequestBody Person person) {
        Employee employee = employeeService.findEmployeeById(id).get();
        person.setEmployee(employee);
        employee.addPerson(person);
        personService.savePerson(person);
        return new ResponseEntity<Employee>(
                employee,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{employeeId}/person/{personId}")
    public ResponseEntity<Employee> updatePersonOfEmployee(@PathVariable int employeeId, @PathVariable int personId,
                                                           @RequestBody Person person) {
        Employee foundEmployee = employeeService.findEmployeeById(employeeId).get();
        Person foundPerson = personService.findPersonById(personId).get();
        foundPerson.setLogin(person.getLogin());
        foundPerson.setPassword(person.getPassword());
        personService.savePerson(foundPerson);
        return new ResponseEntity<Employee>(
                foundEmployee,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{employeeId}/person/{personId}")
    public ResponseEntity<Employee> deletePersonOfEmployee(@PathVariable int employeeId, @PathVariable int personId) {
        Employee foundEmployee = employeeService.findEmployeeById(employeeId).get();
        Person foundPerson = personService.findPersonById(personId).get();
        personService.deletePerson(foundPerson);
        return new ResponseEntity<Employee>(
                foundEmployee,
                HttpStatus.OK
        );
    }
}
