package ru.aasmc.cems.jupiter.testrepos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.aasmc.cems.jupiter.config.TestDbConfig;
import ru.aasmc.cems.jupiter.config.repos.ReposConfig;
import ru.aasmc.cems.repos.PersonRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDbConfig.class, ReposConfig.class})*/
@SpringJUnitConfig(classes = {TestDbConfig.class, ReposConfig.class})
public class RepositoryTest {
    static final Long PERSON_ID = 1L;

    @Autowired
    PersonRepo personRepo;

    @BeforeEach
    void setup() {
        assertNotNull(personRepo);
    }

    @Test
    void testFindByIdPositive() {
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions::fail
        );
    }

    @Test
    void testFindAll() {
        var personSet = personRepo.findAll();
        assertNotNull(personSet);
        assertEquals(2, personSet.size());
    }
}
