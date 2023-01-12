package ru.aasmc.cems.services.simpleimpl;

import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.services.PersonService;

import java.util.Optional;

public class SimplePersonService extends SimpleAbstractService<Person> implements PersonService {
    private PersonRepo repo;

    @Override
    public Person createPerson(String firstName, String lastName) {
        var person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        repo.save(person);
        return person;
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return repo.findByCompleteName(firstName, lastName);
    }

    public void setRepo(PersonRepo repo) {
        this.repo = repo;
    }

    @Override
    AbstractRepo<Person> getRepo() {
        return repo;
    }
}
