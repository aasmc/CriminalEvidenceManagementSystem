package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Person;

import java.util.Optional;

public interface PersonService extends AbstractService<Person> {

    Person createPerson(String firstName, String lastName);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);
}

