package ru.aasmc.ctr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.aasmc.entities.Person;
import ru.aasmc.services.PersonService;

import java.util.Set;

@RestController
@RequestMapping("/person")
public class PersonController {

    final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/all")
    public Set<Person> listData() {
        return personService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable Long id) {
        var opt = personService.findById(id);
        return opt.orElse(null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public Person create(@RequestBody Person person) {
        logger.info("Creating person: {}", person);
        personService.save(person);
        logger.info("Person created successfully with info: {}", person);
        return person;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public void update(@RequestBody Person person, @PathVariable Long id) {
        logger.info("Updating person: " + person);
        personService.save(person);
        logger.info("Person updated successfully with info: {}", person);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Person with id: " + id);
        personService.findById(id).ifPresent(p -> {
            personService.delete(p);
            logger.info("Person deleted successfully");
        });
    }
}
