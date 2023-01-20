package ru.aasmc.cems.services.simpleimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.services.PersonService;

import java.util.Optional;

@Service
public class SimplePersonService extends SimpleAbstractService<Person> implements PersonService {
    private final PersonRepo repo;

    @Autowired
    public SimplePersonService(PersonRepo repo) {
        this.repo = repo;
    }

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

    @Override
    public AbstractRepo<Person> getRepo() {
        return repo;
    }

    @Override
    public Person updateFirstName(Person person, String newFirstname) {
        person.setFirstName(newFirstname);
        return repo.update(person);
    }
}
