package ru.job4j.auth.service;

import org.springframework.stereotype.Service;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        List<Employee> rsl = new ArrayList<>();
        employeeRepository.findAll().forEach(rsl::add);
        return rsl;
    }
}
