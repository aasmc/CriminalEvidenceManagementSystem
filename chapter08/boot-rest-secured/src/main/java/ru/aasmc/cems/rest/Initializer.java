package ru.aasmc.cems.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.cems.rest.person.Person;
import ru.aasmc.cems.rest.person.services.PersonService;
import ru.aasmc.cems.rest.util.DateProcessor;

import javax.annotation.PostConstruct;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class Initializer {
    private Logger logger = LoggerFactory.getLogger(Initializer.class);

    private final PersonService personService;

    public Initializer(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void init() {
        logger.info(" -->> Starting database initialization...");
        if (personService.findAll().isEmpty()) {
            createPersons();
        }
        logger.info(" -->> Database initialization finished.");
    }

    private void createPersons() {
        Person person = new Person();
        person.setUsername("sherlock.holmes");
        person.setFirstName("Sherlock");
        person.setLastName("Holmes");
        person.setPassword("dudu");
        person.setHiringDate(DateProcessor.toDate("1983-08-15 00:25"));
        personService.save(person);

        person = new Person();
        person.setUsername("jackson.brodie");
        person.setFirstName("Jackson");
        person.setLastName("Brodie");
        person.setPassword("bagy");
        person.setHiringDate(DateProcessor.toDate("1983-06-22 00:25"));
        personService.save(person);

        person = new Person();
        person.setUsername("nancy.drew");
        person.setFirstName("Nancy");
        person.setLastName("Drew");
        person.setPassword("dada45");
        person.setHiringDate(DateProcessor.toDate("1990-05-21 00:25"));
        personService.save(person);

        person = new Person();
        person.setUsername("irene.adler");
        person.setFirstName("Irene");
        person.setLastName("Adler");
        person.setPassword("xxxyy");
        person.setHiringDate(DateProcessor.toDate("1987-03-11 00:25"));
        personService.save(person);
    }
}
