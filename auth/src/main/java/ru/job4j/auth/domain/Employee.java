package ru.job4j.auth.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String inn;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created = new GregorianCalendar();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Person> persons = new ArrayList<>();

    public static Employee of(String firstName, String lastName, String inn) {
        Employee employee = new Employee();
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.inn = inn;
        employee.created = Calendar.getInstance();
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(inn, employee.inn) &&
                Objects.equals(created, employee.created) &&
                Objects.equals(persons, employee.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, inn, created, persons);
    }
}
