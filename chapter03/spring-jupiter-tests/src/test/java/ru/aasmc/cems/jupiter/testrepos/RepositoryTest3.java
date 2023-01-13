package ru.aasmc.cems.jupiter.testrepos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.repos.jdbcimpl.JdbcPersonRepo;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = RepositoryTest3.TestCtxConfig.class)
public class RepositoryTest3 {

    static final Long PERSON_ID = 1L;

    @Autowired
    PersonRepo personRepo;

    @BeforeEach
    void setUp() {
        assertNotNull(personRepo);
    }

    @Test
    @Sql(
            scripts = "classpath:db/test-data-one.sql",
            config = @SqlConfig(commentPrefix = "`", separator = "@@")
    )
    void testFindByIdPositive() {
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions::fail
        );
    }

    @Test
    @Sql(
            statements = {"INSERT INTO PERSON(ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, HIRINGDATE, VERSION, CREATED_AT, MODIFIED_AT) VALUES (2, 'irene.adler', 'Irene', 'Adler', 'id123ds', '1990-08-18 00:03', 1, '1990-07-18 00:04', '1990-07-18 00:05');"}
    )
    void testFindByComplete() {
        var personOpt = personRepo.findByCompleteName("Irene", "Adler");
        personOpt.ifPresent(p ->
                assertAll(
                        () -> assertEquals("Irene", p.getFirstName()),
                        () -> assertEquals("Adler", p.getLastName())
                ));
    }

    @Configuration
    static class TestCtxConfig {
        @Bean
        PersonRepo jdbcPersonRepo() {
            return new JdbcPersonRepo(jdbcTemplate());
        }

        @Bean
        JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

        @Bean
        public DataSource dataSource() {
            var builder = new EmbeddedDatabaseBuilder();
            var db = builder
                    .setType(EmbeddedDatabaseType.H2)
                    .generateUniqueName(true)
                    .addScript("db/schema.sql")
                    .build();
            return db;
        }
    }
}
