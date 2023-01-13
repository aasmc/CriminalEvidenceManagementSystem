package ru.aasmc.cems.jupiter.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.aasmc.cems.dao.Person;
import ru.aasmc.cems.repos.PersonRepo;
import ru.aasmc.cems.repos.jdbcimpl.JdbcPersonRepo;
import ru.aasmc.cems.repos.util.PersonRowMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class SpringUnitTest {
    public static final Long PERSON_ID = 1L;

    PersonRepo personRepo;

    @Mock
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        personRepo = new JdbcPersonRepo(jdbcTemplate);
        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(PersonRowMapper.class), any(Long.class)))
                .thenReturn(new Person());
    }

    @Test
    public void testFindByIdPositive() {
        assertTrue(personRepo.findById(PERSON_ID).isPresent());
    }
}
