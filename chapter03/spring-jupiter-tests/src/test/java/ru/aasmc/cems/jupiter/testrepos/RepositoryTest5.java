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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.repos.jdbcimpl.JdbcPersonRepo;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@Sql(
        scripts = {"classpath:db/person-schema.sql","classpath:db/test-data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@SpringJUnitConfig(classes = RepositoryTest5.TestCtxConfig.class)
public class RepositoryTest5 {

    static final Long PERSON_ID = 1L;

    @Autowired
    PersonRepo personRepo;

    @BeforeEach
    void setUp() {
        assertNotNull(personRepo);
    }

    @Test
    void testFindByIdPositive() {
        personRepo.findById(PERSON_ID).ifPresentOrElse(
                p -> assertEquals("Sherlock", p.getFirstName()),
                Assertions:: fail
        );
    }

    @Test
    void testFindAll(){
        var all = personRepo.findAll();
        assertAll(
                () -> assertNotNull(all),
                () -> assertEquals(2, all.size())
        );
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
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .generateUniqueName(true)
                    .build();
        }
    }
}
