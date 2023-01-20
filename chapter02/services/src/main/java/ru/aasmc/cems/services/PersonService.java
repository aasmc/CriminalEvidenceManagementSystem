package ru.aasmc.cems.services;

import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.services.exception.MailSendingException;

import java.util.Optional;
import java.util.Set;

public interface PersonService extends AbstractService<Person> {

    Person createPerson(String firstName, String lastName);

    Optional<Person> findByUsername(String username);

    Optional<Person> findByFirstNameAndLastName(String firstName, String lastName);

    default Person updateUsername(Person person, String newUsername) {
        return null;
    }

    default Person updateFirstName(Person person, String newFirstname) {
        return null;
    }

    default Set<Person> findAll() {
        return Set.of();
    }

    default Person updatePassword(Person person, String password) throws MailSendingException {
        return null;
    }

    default String getPersonAsHtml(String username) {
        return "";
    }
}

