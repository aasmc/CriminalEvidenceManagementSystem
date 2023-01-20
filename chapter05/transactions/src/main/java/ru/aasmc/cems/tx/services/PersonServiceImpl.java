package ru.aasmc.cems.tx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.AbstractRepo;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.services.PersonService;
import ru.aasmc.cems.services.exception.MailSendingException;
import ru.aasmc.cems.services.simpleimpl.SimpleAbstractService;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PersonServiceImpl extends SimpleAbstractService<Person> implements PersonService {

    private PersonRepo personRepo;

    @Autowired
    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Set<Person> findAll() {
        return Set.copyOf(personRepo.findAll());
    }

    //    @Transactional(noRollbackFor = DataIntegrityViolationException.class) // Example of using noRollbackFor
    @Override
    public Person updateUsername(Person person, String newUsername) {
        person.setUsername(newUsername);
        return personRepo.update(person);
    }

    @Transactional(rollbackFor = MailSendingException.class)
    @Override
    public Person updatePassword(Person person, String password) throws MailSendingException {
        person.setPassword(password);
        Person p = personRepo.update(person);
        sendNotification();
        return p;
    }

    private void sendNotification() throws MailSendingException {
        if (true) {
            throw new MailSendingException("Confirmation email for password could not be sent. Password was not updated.");
        }
    }

    @Override
    public Person createPerson(String firstName, String lastName) {
        var person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        personRepo.save(person);
        return person;
    }

    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    @Override
    public String getPersonAsHtml(String username) {
        final StringBuilder sb = new StringBuilder();
        personRepo.findByUsername(username).ifPresentOrElse(
                p -> sb.append("<p>First Name: ").append(p.getFirstName()).append(" </p>")
                        .append("<p>Last Name: ").append(p.getLastName()).append(" </p>"),
                () -> sb.append("<p>None found with username ").append(username).append(" </p>")

        );
        return sb.toString();
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    @Override
    public Optional<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        return personRepo.findByCompleteName(firstName, lastName);
    }

    @Override
    protected AbstractRepo<Person> getRepo() {
        return personRepo;
    }
}
