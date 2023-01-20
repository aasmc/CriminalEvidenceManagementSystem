package ru.aasmc.cems.tx.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.services.DetectiveService;
import ru.aasmc.cems.services.PersonService;
import ru.aasmc.cems.tx.config.AppConfig;
import ru.aasmc.cems.tx.config.TestTransactionalDbConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
public class DetectiveServiceTest {

    private Logger logger = LoggerFactory.getLogger(DetectiveServiceTest.class);

    @Autowired
    @Qualifier("detectiveServiceImpl")
    DetectiveService detectiveService;

    @Autowired
    PersonService personService;

    @Test
    void testFindById() {
        var detective = detectiveService.findById(1L);
        if (detective != null) {
            assertEquals("LD112233", detective.getBadgeNumber());
        } else {
            fail();
        }
    }

    @Test
    void testDetectiveHtml() {
        var d = detectiveService.findById(1L);
        if (d != null) {
            Person p = d.getPerson();
            assertNotNull(p);
            String html = personService.getPersonAsHtml(p.getUsername());
            logger.info("Detective personal info: {}", html);
        } else {
            fail();
        }
    }
}















