package ru.aasmc.cems.dj.services;

import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.dj.problem.InvalidCriteriaException;
import ru.aasmc.cems.dto.CriteriaDto;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> findAll();

    long countPersons();

    Optional<Person> findById(Long id);

    Person save(Person person);

    Person updateFirstName(Person person, String newFirstname);

    void delete(Person person);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByCompleteName(String firstName, String lastName);

    String getPersonAsHtml(String username);

    List<Person> getByCriteriaDto(CriteriaDto criteria) throws InvalidCriteriaException;

    //Person updatePassword(Person person, String password)throws MailSendingException;
}

