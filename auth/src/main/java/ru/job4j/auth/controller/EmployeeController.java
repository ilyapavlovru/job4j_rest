package ru.job4j.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Report;
import ru.job4j.auth.service.EmployeeService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // метод получения всех сотрудников со всеми их аккаунтами
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAll() {

        Collection<Employee> employees = employeeService.findAllEmployees();

        return new ResponseEntity<List<Employee>>(
                employeeService.findAllEmployees(),
                HttpStatus.OK);
    }

    // добавление нового аккаунта для сотрудника
//    @PostMapping("/")
//    public ResponseEntity<Person> create(@RequestBody Person person) {
//        return new ResponseEntity<Person>(
//                this.personService.savePerson(person),
//                HttpStatus.CREATED
//        );
//    }
}
