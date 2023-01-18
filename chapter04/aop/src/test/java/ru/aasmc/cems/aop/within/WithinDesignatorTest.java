package ru.aasmc.cems.aop.within;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aasmc.cems.aop.test.TestDbConfig;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.services.PersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDbConfig.class, WithinConfig.class})
public class WithinDesignatorTest {

    @Autowired
    PersonRepo personRepo;

    @Autowired
    PersonService personService;

    @Test
    void testFindById() {
        personRepo.findById(1L).ifPresentOrElse(
                p -> assertEquals("sherlock.holmes", p.getUsername()),
                () -> fail("Person not found!")
        );
    }

    @Test
    void testFindByCompleteName() {
        personService.findByFirstNameAndLastName("Sherlock", "Holmes").ifPresent(person -> {
            assertEquals("sherlock.holmes", person.getUsername());
        });
    }
}
