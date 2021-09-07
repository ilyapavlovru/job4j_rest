package ru.job4j.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

@SpringBootApplication
public class AuthApplication {

    private static final Logger logger = LoggerFactory.getLogger(AuthApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(PersonRepository personRepository) {
        return (args) -> {
            personRepository.save(new Person(1,"pavlovi", "pass"));
            personRepository.save(new Person(2,"arsentevp", "pass"));
            personRepository.save(new Person(3,"ivanovi", "pass"));
            logger.info("The sample data has been generated");
        };
    }

    @Bean
    public RestTemplate getTemplate() {
        return new RestTemplate();
    }
}
