package ru.aasmc.cems.repos.jdbcimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.aasmc.cems.dao.Storage;
import ru.aasmc.cems.repos.StorageRepo;
import ru.aasmc.cems.repos.util.StorageRowMapper;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class JdbcStorageRepo extends JdbcAbstractRepo<Storage> implements StorageRepo {
    private RowMapper<Storage> rowMapper = new StorageRowMapper();

    public JdbcStorageRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Optional<Storage> findById(Long entityId) {
        var sql = "select ID, NAME, LOCATION from STORAGE where ID= ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, entityId));
    }

    @Override
    public Optional<Storage> findByName(String name) {
        var sql = "select ID, NAME, LOCATION from STORAGE where NAME= ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, name));
    }

    @Override
    public Optional<Storage> findByLocation(String location) {
        var sql = "select ID, NAME, LOCATION from STORAGE where LOCATION= ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, location));
    }

    @Override
    public Storage save(Storage storage) {
        jdbcTemplate.update(
                "insert into STORAGE(ID, NAME, LOCATION, MODIFIED_AT, CREATED_AT) " +
                        "values(?,?,?,?,?)",
                storage.getId(), storage.getName(), storage.getLocation(), LocalDateTime.now(), LocalDateTime.now()
        );
        return storage;
    }

    @Override
    public void delete(Storage entity) {
        jdbcTemplate.update("delete from STORAGE where ID =? ",
                entity.getId());
    }

    @Override
    public Storage update(Storage entity) {
        return null;
    }

    @Override
    public int deleteById(Long entityId) {
        return jdbcTemplate.update("delete from " +
                "STORAGE where ID =? ", entityId);
    }
}





















