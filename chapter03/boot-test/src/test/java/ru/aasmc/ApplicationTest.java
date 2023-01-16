package ru.aasmc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.aasmc.entities.Person;
import ru.aasmc.services.PersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ApplicationTest {

    @Autowired
    private PersonService personService;

    @Rollback
    @Transactional
    @Test
    void testSavePerson() {
        var person = new Person();
        person.setFirstName("Irene");
        person.setLastName("Adler");
        personService.save(person);

        assertEquals(2, personService.count());
    }

    @Test
    void testCount() {
        assertEquals(1, personService.count());
    }
}
