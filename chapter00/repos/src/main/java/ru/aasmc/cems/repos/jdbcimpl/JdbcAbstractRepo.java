package ru.aasmc.cems.repos.jdbcimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.aasmc.cems.dao.AbstractEntity;
import ru.aasmc.cems.repos.AbstractRepo;

import java.util.Optional;

public class JdbcAbstractRepo <T extends AbstractEntity> implements AbstractRepo<T> {

    protected JdbcTemplate jdbcTemplate;

    public JdbcAbstractRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public T update(T entity) {
        return null;
    }

    @Override
    public int deleteById(Long entityId) {
        return 0;
    }

    @Override
    public Optional<T> findById(Long entityId) {
        return Optional.empty();
    }
}
