package ru.aasmc.cems.tx.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.aasmc.cems.services.PersonService;
import ru.aasmc.cems.services.exception.MailSendingException;
import ru.aasmc.cems.tx.config.AppConfig;
import ru.aasmc.cems.tx.config.TestTransactionalDbConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
public class PersonServiceTest {
    private Logger logger = LoggerFactory.getLogger(PersonServiceTest.class);

    @Autowired
    PersonService personService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @Sql(statements = {"drop table NEW_PERSON if exists;"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testCreateTable(){
        jdbcTemplate.execute("create table NEW_PERSON(" +
                "  ID BIGINT IDENTITY PRIMARY KEY " +
                ", USERNAME VARCHAR2(50) NOT NULL " +
                ", FIRSTNAME VARCHAR2(50) " +
                ", LASTNAME VARCHAR2(50) " +
                ", UNIQUE(USERNAME)) ");
        long result = jdbcTemplate.queryForObject("select count(*) from NEW_PERSON", Long.class);
        // table exists but is empty
        assertEquals(0, result);
    }

    @Test
    void testUpdatePassword() {
        var p = personService.findById(2L);
        assertThrows(
                MailSendingException.class,
                () -> personService.updatePassword(p, "test_pass")
        );
    }

    @Test
    void testUpdateUsername() {
        var p = personService.findById(1L);
        assertThrows(
                DataIntegrityViolationException.class,
                () -> personService.updateUsername(p, "irene.adler")
        );
        //making sure rollback did not affect anything
        var pp = personService.findById(1L);
        logger.info("->> {}" , pp.toString());
    }
}
