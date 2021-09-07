package ru.job4j.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.EmployeeRepository;
import ru.job4j.auth.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AuthApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner setup(EmployeeRepository employeeRepository, PersonRepository personRepository) {
//        return (args) -> {
//
//            Employee employee = Employee.of("ilya", "pavlov", "inn123456");
//
//            Person person1 = new Person("pavlovia-1", "123");
//            Person person2 = new Person("pavlovia-2", "123");
//            person1.setEmployee(employee);
//            person2.setEmployee(employee);
//
//
//            employeeRepository.save(employee);
//
//
//            List<Employee> rsl = new ArrayList<>();
//            employeeRepository.findAll().forEach(rsl::add);
//
//            List<Person> persons = rsl.get(0).getPersons();
//
//            logger.info("The sample data has been generated");
//        };
//    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
