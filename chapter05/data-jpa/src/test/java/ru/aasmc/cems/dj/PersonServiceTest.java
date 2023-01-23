package ru.aasmc.cems.dj;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aasmc.cems.dj.config.DataSourceConfig;
import ru.aasmc.cems.dj.services.DetectiveService;
import ru.aasmc.cems.dj.services.PersonService;
import ru.aasmc.cems.dj.services.StorageService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DataSourceConfig.class, ServiceConfig.class})
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    StorageService storageService;

    @Autowired
    DetectiveService detectiveService;

    @Test
    void testFindById() {
        // because 1L is the Storage
        personService.findById(2L).ifPresentOrElse(
                p -> assertEquals("sherlock.holmes", p.getUsername()),
                () -> fail("Person not found!")
        );
    }

    @Test
    void testfindByCompleteName() {
        personService.findByCompleteName("Sherlock", "Holmes").ifPresent(person ->
                assertEquals("sherlock.holmes", person.getUsername())
        );
    }

    @Test
    void testFindAll() {
        assertNotNull(personService.findAll());
    }
}
