package ru.aasmc.services;

import ru.aasmc.entities.Person;
import ru.aasmc.repos.PersonRepo;

import java.util.Optional;
import java.util.Set;

public interface PersonService {
    Set<Person> findAll();

    long count();

    Optional<Person> findById(Long id);

    Person save(Person person);

    void delete(Person person);
}
